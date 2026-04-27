package com.felipe.br.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.br.dto.ClienteRequestDTO;
import com.felipe.br.dto.ClienteResponseDTO;
import com.felipe.br.dto.ClienteUpdateDTO;
import com.felipe.br.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping("/home")
	public String paginaInicial() {
		return "PrjCafeDev/index";
	}
	
	@PostMapping("/salvar")
	public ClienteResponseDTO salvarCliente(@RequestBody ClienteRequestDTO cliente) {
		return clienteService.saveCliente(cliente);
	}
	
	@GetMapping("/buscar/{id}")
	public ClienteResponseDTO buscarClientePorId(@PathVariable Long id) {
		return clienteService.findClienteById(id);
	}
	
	@GetMapping("/buscar")
	public List<ClienteResponseDTO> buscarTodosClientes() {
		return clienteService.findAllClientes();
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletarClientePorId(@PathVariable Long id) {
		clienteService.deleteClienteById(id);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<ClienteResponseDTO> atualizarClientePorId(@RequestBody ClienteUpdateDTO clienteNovo, @PathVariable Long id) {
		ClienteResponseDTO atualizado = clienteService.updateClienteById(clienteNovo, id);
		
		if (atualizado != null) {
			return ResponseEntity.ok(atualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
