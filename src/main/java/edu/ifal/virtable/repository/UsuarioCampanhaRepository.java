package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.model.campanha.UsuarioCampanha;

public interface UsuarioCampanhaRepository extends JpaRepository<UsuarioCampanha, Long> {
}