package com.felipe.br.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.br.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
