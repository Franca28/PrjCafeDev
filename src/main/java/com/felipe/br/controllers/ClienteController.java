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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.br.dto.ClienteRequestDTO;
import com.felipe.br.dto.ClienteResponseDTO;
import com.felipe.br.dto.ClienteUpdateDTO;
import com.felipe.br.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Endpoints para gestão de clientes")
public class ClienteController {
	
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Operation(summary = "Cria um novo cliente", description = "Recebe os dados e salva um cliente na base de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
	})
	@PostMapping("/salvar")
	public ClienteResponseDTO salvarCliente(@RequestBody ClienteRequestDTO cliente) {
		return clienteService.saveCliente(cliente);
	}
	
	@Operation(summary = "Busca cliente por ID", description = "Retorna os detalhes de um único cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado")
		})
	@GetMapping("/buscar/{id}")
	public ClienteResponseDTO buscarClientePorId(@PathVariable Long id) {
		return clienteService.findClienteById(id);
	}
	
	@Operation(summary = "Busca clientes por nome", description = "Filtra clientes que contenham o nome enviado por parâmetro")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
			@ApiResponse(responseCode = "404", description = "Clientes não encontrados")
		})
	@GetMapping("/buscar-por-nome")
	public List<ClienteResponseDTO> buscarClientesPorNome(@RequestParam String nome) {
		return clienteService.findClientesByNome(nome);
	}
	
	@Operation(summary = "Busca cliente e seus pedidos", description = "Retorna os dados do cliente juntamente com a sua lista de pedidos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado")
		})
	@GetMapping("/buscar-com-pedidos/{id}")
	public ClienteResponseDTO buscarClienteComPedidos(@PathVariable Long id) {
		return clienteService.findClienteByIdWithPedidos(id);
	}
	
	@Operation(summary = "Lista todos os clientes", description = "Retorna uma lista completa de todos os clientes registrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
			@ApiResponse(responseCode = "404", description = "Clientes não encontrados")
		})
	@GetMapping("/buscar")
	public List<ClienteResponseDTO> buscarTodosClientes() {
		return clienteService.findAllClientes();
	}
	
	@Operation(summary = "Elimina um cliente", description = "Remove o registro do cliente permanentemente através do ID")
	@DeleteMapping("/deletar/{id}")
	public void deletarClientePorId(@PathVariable Long id) {
		clienteService.deleteClienteById(id);
	}
	
	@Operation(summary = "Atualiza um cliente", description = "Modifica os dados de um cliente existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado")
	})
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