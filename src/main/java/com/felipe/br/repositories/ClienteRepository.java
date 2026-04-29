package com.felipe.br.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.felipe.br.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE LOWER(c.nome) = LOWER(:nome)")
	List<Cliente> findClienteByNome(@Param("nome") String nome);
	
	@Query("SELECT c FROM Cliente c JOIN FETCH c.pedidos WHERE c.id = :id")
	Optional<Cliente> findClienteByIdWithPedidos(Long id);

}
