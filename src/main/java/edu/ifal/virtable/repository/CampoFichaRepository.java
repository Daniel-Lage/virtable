package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.ficha.CampoFicha;

public interface CampoFichaRepository extends JpaRepository<CampoFicha, Long> {
}
