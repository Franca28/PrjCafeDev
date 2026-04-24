package com.felipe.br.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.br.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
