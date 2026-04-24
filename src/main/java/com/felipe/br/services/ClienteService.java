package com.felipe.br.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.felipe.br.dto.ClienteRequestDTO;
import com.felipe.br.dto.ClienteResponseDTO;
import com.felipe.br.dto.ClienteUpdateDTO;
import com.felipe.br.entities.Cliente;
import com.felipe.br.mappers.ClienteMapper;
import com.felipe.br.repositories.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;

	public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
	}

	public ClienteResponseDTO saveCliente(ClienteRequestDTO dto) {
		
		Cliente cliente = clienteRepository.save(clienteMapper.toEntity(dto));
		
		return clienteMapper.toResponse(cliente);
	}

	public ClienteResponseDTO findClienteById(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		return clienteMapper.toResponse(cliente);
	}

	public List<ClienteResponseDTO> findAllClientes() {
		return clienteRepository.findAll()
				.stream()
				.map(cliente -> clienteMapper.toResponse(cliente))
				.collect(Collectors.toList());
	}

	public void deleteClienteById(Long id) {
		clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		clienteRepository.deleteById(id);
	}

	public ClienteResponseDTO updateClienteById(ClienteUpdateDTO newCliente, Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		cliente.setNome(newCliente.getNome());
		cliente.setEmail(newCliente.getEmail());

		clienteRepository.save(cliente);

		return clienteMapper.toResponse(cliente);
	}
}
