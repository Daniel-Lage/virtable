package edu.ifal.virtable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.domain.campanha.Campanha;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
}
