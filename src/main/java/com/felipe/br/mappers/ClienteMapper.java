package com.felipe.br.mappers;

import org.springframework.stereotype.Component;

import com.felipe.br.dto.ClienteRequestDTO;
import com.felipe.br.dto.ClienteResponseDTO;
import com.felipe.br.entities.Cliente;

@Component
public class ClienteMapper {
	
	public Cliente toRequest(ClienteRequestDTO clienteRequest) {
		if (clienteRequest == null) {
			return null;
		}
		
		return new Cliente(clienteRequest.getNome(), clienteRequest.getEmail());
	}

	public ClienteResponseDTO toResponse(Cliente cliente) {

		if (cliente == null) {
			return null;
		}

		return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
	}
}
