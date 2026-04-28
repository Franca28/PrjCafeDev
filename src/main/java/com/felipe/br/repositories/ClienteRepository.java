package com.felipe.br.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.felipe.br.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT c FROM Cliente c JOIN FETCH c.pedidos WHERE c.nome = :nome")
	List<Cliente> findClienteByNome(String nome);
	
	@Query("SELECT c FROM Cliente c JOIN FETCH c.pedidos WHERE c.id = :id")
	Optional<Cliente> findClienteByIdWithPedidos(Long id);

}
