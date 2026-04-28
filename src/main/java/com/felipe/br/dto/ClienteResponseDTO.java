package com.felipe.br.dto;

import java.util.List;

public class ClienteResponseDTO {
	
	private Long id;
	private String nome;
	private String email;
	
	private List<PedidoResponseDTO> pedidos;

	public ClienteResponseDTO() {
	}

	public ClienteResponseDTO(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public ClienteResponseDTO(Long id, String nome, String email, List<PedidoResponseDTO> pedidos) {
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.pedidos = pedidos;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PedidoResponseDTO> getPedidos() {
		return pedidos;
	}

}