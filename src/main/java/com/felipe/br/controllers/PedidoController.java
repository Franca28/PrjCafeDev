package com.felipe.br.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.br.dto.PedidoRequestDTO;
import com.felipe.br.dto.PedidoResponseDTO;
import com.felipe.br.dto.PedidoUpdateDTO;
import com.felipe.br.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "Endpoints para gestão de pedidos")
public class PedidoController {

	private final PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@Operation(summary = "Registra um novo pedido", description = "Cria um novo pedido associado a um cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro na validação dos dados do pedido") })
	@PostMapping("/salvar")
	public ResponseEntity<PedidoResponseDTO> salvarPedido(@RequestBody PedidoRequestDTO pedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.savePedido(pedido));
	}

	@Operation(summary = "Procura um pedido pelo ID", description = "Retorna os detalhes de um pedido específico")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
			@ApiResponse(responseCode = "404", description = "Pedido não existe no sistema") })
	@GetMapping("/buscar/{id}")
	public ResponseEntity<PedidoResponseDTO> buscarPedidoPorId(@PathVariable Long id) {
		PedidoResponseDTO resultado = pedidoService.findPedidoById(id);

		if (resultado != null) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Lista todos os pedidos", description = "Retorna uma lista de todos os pedidos registrados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
			@ApiResponse(responseCode = "404", description = "Não há pedidos no sistema") })
	@GetMapping("/buscar")
	public ResponseEntity<List<PedidoResponseDTO>> buscarTodosPedidos() {
		List<PedidoResponseDTO> resultado = pedidoService.findAllPedidos();
		return ResponseEntity.ok(resultado);
	}

	@Operation(summary = "Elimina um pedido", description = "Remove permanentemente um pedido através do seu ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Pedido removido com sucesso"),
			@ApiResponse(responseCode = "404", description = "Não foi possível encontrar o pedido para remover") })
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarPedidoPorId(@PathVariable Long id) {
		pedidoService.deletePedidoById(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Atualiza um pedido existente", description = "Altera as informações de um pedido já registrado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Não foi possível encontrar o pedido para atualizar") })
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<PedidoResponseDTO> atualizarPedidoPorId(@RequestBody PedidoUpdateDTO pedidoNovo,
			@PathVariable Long id) {
		PedidoResponseDTO atualizado = pedidoService.updatePedidoById(pedidoNovo, id);

		if (atualizado != null) {
			return ResponseEntity.ok(atualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
