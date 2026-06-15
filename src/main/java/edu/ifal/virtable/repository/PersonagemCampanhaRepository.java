package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.model.campanha.PersonagemCampanha;

public interface PersonagemCampanhaRepository extends JpaRepository<PersonagemCampanha, Long> {
}