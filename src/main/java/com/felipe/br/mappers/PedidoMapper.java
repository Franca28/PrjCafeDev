package com.felipe.br.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.felipe.br.dto.ClienteResponseDTO;
import com.felipe.br.dto.PedidoRequestDTO;
import com.felipe.br.dto.PedidoResponseDTO;
import com.felipe.br.entities.Cliente;
import com.felipe.br.entities.Pedido;

@Component
public class PedidoMapper {

	private final ClienteMapper clienteMapper;

	public PedidoMapper(ClienteMapper clienteMapper) {
		this.clienteMapper = clienteMapper;
	}

	public Pedido toEntity(PedidoRequestDTO pedidoRequest, Cliente clienteRef, LocalDateTime dataPedido) {
		
		if (pedidoRequest == null) {
			return null;
		}
		
		return new Pedido(pedidoRequest.getDescricao(), pedidoRequest.getValorTotal(), dataPedido, clienteRef);
	}

	public PedidoResponseDTO toResponse(Pedido pedido) {

		if (pedido == null) {
			return null;
		}

		ClienteResponseDTO clienteDTO = clienteMapper.toResponse(pedido.getCliente());

		return new PedidoResponseDTO(pedido.getId(), pedido.getDescricao(), pedido.getValorTotal(),
				pedido.getDataPedido(), clienteDTO);
	}
}
