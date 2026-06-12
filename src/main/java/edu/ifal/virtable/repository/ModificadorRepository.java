package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.dado.Modificador;

public interface ModificadorRepository extends JpaRepository<Modificador, Long> {
}
