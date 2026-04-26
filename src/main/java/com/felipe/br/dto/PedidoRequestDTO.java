package com.felipe.br.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoRequestDTO {

	private Long clienteId;

	@NotBlank
	private String descricao;

	@NotNull
	private Double valorTotal;

	public PedidoRequestDTO(Long clienteId, @NotBlank String descricao, @NotNull Double valorTotal) {
		this.clienteId = clienteId;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
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

}
