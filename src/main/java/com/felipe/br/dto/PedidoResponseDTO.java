package com.felipe.br.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoResponseDTO {

	private Long id;
	private String descricao;
	private Double valorTotal;
	
	@JsonFormat(pattern = "dd/MM HH:mm:ss")
	private LocalDateTime dataPedido;
	
	private ClienteResponseDTO cliente;
	
	public PedidoResponseDTO() {
	}

	public PedidoResponseDTO(Long id, String descricao, Double valorTotal, LocalDateTime dataPedido, ClienteResponseDTO cliente) {
		this.id = id;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.dataPedido = dataPedido;
		this.cliente = cliente;
	}
	
	public PedidoResponseDTO(Long id, String descricao, Double valorTotal, LocalDateTime dataPedido) {
		this.id = id;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.dataPedido = dataPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public ClienteResponseDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResponseDTO cliente) {
		this.cliente = cliente;
	}
}
