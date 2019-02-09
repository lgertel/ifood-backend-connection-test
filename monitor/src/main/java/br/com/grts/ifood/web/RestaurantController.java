package br.com.grts.ifood.web;

import br.com.grts.ifood.coreapi.command.CreateRestaurantCommand;
import br.com.grts.ifood.coreapi.queries.FindAllRestaurantsQuery;
import br.com.grts.ifood.coreapi.queries.Restaurant;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  @PostMapping
  public void createRestaurant() {
    String restaurantId = UUID.randomUUID().toString();
    commandGateway.send(new CreateRestaurantCommand(restaurantId, "BurgerKing"));
  }

  @GetMapping
  public List<Restaurant> findAllRestaurants() {
    return queryGateway.query(new FindAllRestaurantsQuery(),
        ResponseTypes.multipleInstancesOf(Restaurant.class)).join();
  }

}
