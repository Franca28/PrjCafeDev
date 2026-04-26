package com.felipe.br.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoUpdateDTO {
	
	private Long clienteId;

	@NotBlank
	private String descricao;

	@NotNull
	private Double valorTotal;

	@NotNull
	private LocalDateTime dataPedidoAtualizada;

	public PedidoUpdateDTO() {
	}

	public PedidoUpdateDTO(Long clienteId, @NotBlank String descricao, @NotNull Double valorTotal,
			@NotNull LocalDateTime dataPedidoAtualizada) {
		this.clienteId = clienteId;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.dataPedidoAtualizada = dataPedidoAtualizada;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataPedidoAtualizada() {
		return dataPedidoAtualizada;
	}

	public void setDataPedidoAtualizada(LocalDateTime dataPedidoAtualizada) {
		this.dataPedidoAtualizada = dataPedidoAtualizada;
	}
}
