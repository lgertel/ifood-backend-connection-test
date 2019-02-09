package br.com.grts.repository;

import br.com.grts.model.Unavailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnavailabilityRepository extends JpaRepository<Unavailability, Long> {
}
