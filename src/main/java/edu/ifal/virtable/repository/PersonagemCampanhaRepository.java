package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.campanha.PersonagemCampanha;

public interface PersonagemCampanhaRepository extends JpaRepository<PersonagemCampanha, Long> {
}