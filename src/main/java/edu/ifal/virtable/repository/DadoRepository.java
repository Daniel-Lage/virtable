package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.dado.Dado;

public interface DadoRepository extends JpaRepository<Dado, Long> {
}
