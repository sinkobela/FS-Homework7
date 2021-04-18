package ro.fasttrackit.homework7.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.homework7.restaurant.domain.CityProvider;
import ro.fasttrackit.homework7.restaurant.domain.Restaurant;
import ro.fasttrackit.homework7.restaurant.exceptions.ResourceNotFoundException;
import ro.fasttrackit.homework7.restaurant.exceptions.ValidationException;
import ro.fasttrackit.homework7.restaurant.repository.RestaurantRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class RestaurantValidator {
    private final RestaurantRepository repository;
    private final CityProvider cityProvider;

    private void validateRestaurantName(Restaurant restaurant) {
        if (repository.existsByNameIgnoreCase(restaurant.getName())) {
            throw new ValidationException("Restaurant with name: " + restaurant.getName() + " already exists!");
        } else if (restaurant.getName() == null) {
            throw new ResourceNotFoundException("Restaurant name cannot be null!");
        }
    }

    private void validateRestaurantSince(Restaurant restaurant) {
        if (restaurant.getSince().isAfter(LocalDate.now())) {
            throw new ValidationException("Restaurant since cannot be from the future!");
        } else if (restaurant.getSince() == null) {
            throw new ResourceNotFoundException("Restaurant since cannot be null!");
        }
    }

    private void validateCity(Restaurant restaurant) {
        if (!cityProvider.getCities().contains(restaurant.getCity())) {
            throw new ValidationException("There is no " + restaurant.getCity() + " city in this country!");
        } else if (restaurant.getCity() == null) {
            throw new ResourceNotFoundException("Restaurant city cannot be null!");
        }
    }

    public void validate(Restaurant restaurant) {
        validateRestaurantName(restaurant);
        validateRestaurantSince(restaurant);
        validateCity(restaurant);
    }
}
