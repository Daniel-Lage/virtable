package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.model.sistema.SistemaRPG;

public interface SistemaRPGRepository extends JpaRepository<SistemaRPG, Long> {
}