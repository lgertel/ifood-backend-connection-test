package br.com.grts.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.time.LocalDateTime

data class CreateRestaurantCommand(
    @TargetAggregateIdentifier val restaurantId: String,
    val name: String
)

data class ScheduleUnavailabilityCommand(
    @TargetAggregateIdentifier
    val scheduleUnavailabilityId: String,
    val restaurantId: String,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val createdAt: LocalDateTime
)

data class ToggleRestaurantOnlineCommand(
    @TargetAggregateIdentifier val restaurantId: String,
    val time: LocalDateTime
)

data class ToggleRestaurantOfflineCommand(
    @TargetAggregateIdentifier val restaurantId: String
)