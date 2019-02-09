package br.com.grts.controller;

import br.com.grts.model.Unavailability;
import br.com.grts.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

  private final RestaurantRepository restaurantRepository;

  @GetMapping("/unavailabilities/{axonId}")
  public Set<Unavailability> findByUnavailabilitiesByRestaurant(@PathVariable String axonId) {
    return restaurantRepository.findByAxonId(axonId).getUnavailabilities();
  }
}
