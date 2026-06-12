package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.campanha.UsuarioCampanha;

public interface UsuarioCampanhaRepository extends JpaRepository<UsuarioCampanha, Long> {
}