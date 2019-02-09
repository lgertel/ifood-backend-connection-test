package br.com.grts.web;

import br.com.grts.coreapi.*;
import br.com.grts.coreapi.queries.Restaurant;
import br.com.grts.coreapi.queries.ScheduleUnavailability;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  @PostMapping
  public String createRestaurant() {
    String restaurantId = UUID.randomUUID().toString();
    commandGateway.send(new CreateRestaurantCommand(restaurantId, "BurgerKing"));

    return restaurantId;
  }

  @GetMapping
  public List<Restaurant> findAllRestaurants() {
    return queryGateway.query(new FindAllRestaurantsQuery(),
        ResponseTypes.multipleInstancesOf(Restaurant.class)).join();
  }

  @PostMapping("/schedule/{restaurantId}")
  public void scheduleUnavailability(@PathVariable String restaurantId) {
    commandGateway.send(
        new ScheduleUnavailabilityCommand(
            UUID.randomUUID().toString(),
            restaurantId,
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now()
        )
    );
  }

  @GetMapping("/schedule")
  public List<ScheduleUnavailability> findAllScheduleUnavailabilities() {
    return queryGateway.query(new FindAllScheduleUnavailabilitiesQuery(),
        ResponseTypes.multipleInstancesOf(ScheduleUnavailability.class)).join();
  }

  @PostMapping("/heartbeat/{restaurantId}")
  public void heartbeat(@PathVariable String restaurantId) {
    commandGateway.send(new ToggleRestaurantOnlineCommand(restaurantId, LocalDateTime.now()));
  }

}
