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

import com.felipe.br.dto.PedidoRequestDTO;
import com.felipe.br.dto.PedidoResponseDTO;
import com.felipe.br.dto.PedidoUpdateDTO;
import com.felipe.br.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	private final PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@PostMapping("/salvar")
	public PedidoResponseDTO salvarPedido(@RequestBody PedidoRequestDTO pedido) {
		return pedidoService.savePedido(pedido);
	}

	@GetMapping("/buscar/{id}")
	public PedidoResponseDTO buscarPedidoPorId(@PathVariable Long id) {
		return pedidoService.findPedidoById(id);
	}

	@GetMapping("/buscar")
	public List<PedidoResponseDTO> buscarTodosPedidos() {
		return pedidoService.findAllPedidos();
	}

	@DeleteMapping("/deletar/{id}")
	public void deletarPedidoPorId(@PathVariable Long id) {
		pedidoService.deletePedidoById(id);
	}

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
