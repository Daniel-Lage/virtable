package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.sistema.SistemaRPG;

public interface SistemaRPGRepository extends JpaRepository<SistemaRPG, Long> {
}