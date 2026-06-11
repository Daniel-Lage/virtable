package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.personagem.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}