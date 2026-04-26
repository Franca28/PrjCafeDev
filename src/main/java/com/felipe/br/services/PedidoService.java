package com.felipe.br.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.felipe.br.dto.PedidoRequestDTO;
import com.felipe.br.dto.PedidoResponseDTO;
import com.felipe.br.dto.PedidoUpdateDTO;
import com.felipe.br.entities.Cliente;
import com.felipe.br.entities.Pedido;
import com.felipe.br.mappers.PedidoMapper;
import com.felipe.br.repositories.ClienteRepository;
import com.felipe.br.repositories.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final PedidoMapper pedidoMapper;

	public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,
			PedidoMapper pedidoMapper) {
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
		this.pedidoMapper = pedidoMapper;
	}

	public PedidoResponseDTO savePedido(PedidoRequestDTO dto) {
		Cliente cliente = clienteRepository.findById(dto.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		Pedido pedido = pedidoRepository.save(pedidoMapper.toEntity(dto, cliente, LocalDateTime.now()));

		return pedidoMapper.toResponse(pedido);
	}

	public PedidoResponseDTO findPedidoById(Long id) {

		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		return pedidoMapper.toResponse(pedido);
	}

	public List<PedidoResponseDTO> findAllPedidos() {
		return pedidoRepository.findAll().stream().map(pedido -> pedidoMapper.toResponse(pedido))
				.collect(Collectors.toList());
	}

	public void deletePedidoById(Long id) {
		pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		pedidoRepository.deleteById(id);
	}

	public PedidoResponseDTO updatePedidoById(PedidoUpdateDTO newPedido, Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		Cliente newCliente = clienteRepository.findById(newPedido.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		pedido.setCliente(newCliente);
		pedido.setDescricao(newPedido.getDescricao());
		pedido.setValorTotal(newPedido.getValorTotal());
		pedido.setDataPedido(LocalDateTime.now());

		pedidoRepository.save(pedido);

		return pedidoMapper.toResponse(pedido);
	}
}
