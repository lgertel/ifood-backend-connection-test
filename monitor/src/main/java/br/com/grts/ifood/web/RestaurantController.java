package br.com.grts.ifood.web;

import br.com.grts.ifood.coreapi.command.CreateRestaurantCommand;
import br.com.grts.ifood.coreapi.command.ScheduleUnavailabilityCommand;
import br.com.grts.ifood.coreapi.queries.*;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

}
