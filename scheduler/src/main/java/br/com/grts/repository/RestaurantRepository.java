package br.com.grts.repository;

import br.com.grts.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
  Restaurant findByAxonId(String id);
}
