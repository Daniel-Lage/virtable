package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.model.ficha.ValorCampo;

public interface ValorCampoRepository extends JpaRepository<ValorCampo, Long> {
}