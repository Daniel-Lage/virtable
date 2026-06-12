package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.ficha.ValorCampo;

public interface ValorCampoRepository extends JpaRepository<ValorCampo, Long> {
}