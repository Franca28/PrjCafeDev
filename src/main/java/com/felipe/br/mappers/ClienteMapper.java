package com.felipe.br.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.felipe.br.dto.ClienteRequestDTO;
import com.felipe.br.dto.ClienteResponseDTO;
import com.felipe.br.dto.PedidoResponseDTO;
import com.felipe.br.entities.Cliente;

@Component
public class ClienteMapper {
	
	public Cliente toEntity(ClienteRequestDTO clienteRequest) {
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
	
	public ClienteResponseDTO toResponseWithPedidos(Cliente clientes) {
		
		if (clientes == null) {
			return null;
		}
		
		List<PedidoResponseDTO> pedidosResponse = clientes.getPedidos().stream()
				.map(pedido -> new PedidoResponseDTO(pedido.getId(), pedido.getDescricao(), pedido.getValorTotal(), pedido.getDataPedido()))
				.toList();
		
		return new ClienteResponseDTO(clientes.getId(), clientes.getNome(), clientes.getEmail(), pedidosResponse);
	}
}
