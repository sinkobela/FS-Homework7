package ro.fasttrackit.homework7.restaurant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.homework7.restaurant.domain.Restaurant;
import ro.fasttrackit.homework7.restaurant.exceptions.ResourceNotFoundException;
import ro.fasttrackit.homework7.restaurant.repository.RestaurantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    private final ObjectMapper mapper;
    private final RestaurantValidator validator;


    public Page<Restaurant> getAll(RestaurantFilters filters, Pageable pageable) {
        if (filters.getStars() == null && filters.getCity() == null) {
            return repository.findAll(pageable);
        } else if (filters.getStars() != null && filters.getCity() == null) {
            return getByStars(filters.getStars(), pageable);
        } else if (filters.getStars() == null) {
            return getByCity(filters.getCity(), pageable);
        } else {
            return getByStarsAndCity(filters.getStars(), filters.getCity(), pageable);
        }
    }

    private Page<Restaurant> getByStars(List<Integer> stars, Pageable pageable) {
        return repository.findAllByStarsIn(stars, pageable);
    }

    private Page<Restaurant> getByCity(String city, Pageable pageable) {
        return repository.findAllByCityIgnoreCase(city, pageable);
    }

    private Page<Restaurant> getByStarsAndCity(List<Integer> stars, String city, Pageable pageable) {
        return repository.findAllByStarsInAndCityIgnoreCase(stars, city, pageable);
    }

    public Restaurant getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find restaurant with id: " + id + "!"));
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        validator.validate(restaurant);
        return repository.save(restaurant);
    }

    public Restaurant replaceRestaurant(Long id, Restaurant restaurant) {
        validator.validate(restaurant);
        Restaurant newRestaurant = getById(id);
        newRestaurant.setName(restaurant.getName());
        newRestaurant.setStars(restaurant.getStars());
        newRestaurant.setCity(restaurant.getCity());
        return addRestaurant(newRestaurant);
    }

    @SneakyThrows
    public Restaurant patchRestaurant(Long id, JsonPatch patch) {
        Restaurant oldRestaurant = getById(id);
        JsonNode patchedRestaurantJson = patch.apply(mapper.valueToTree(oldRestaurant));
        Restaurant patchedRestaurant = mapper.treeToValue(patchedRestaurantJson, Restaurant.class);
        return replaceRestaurant(id, patchedRestaurant);
    }

    public Restaurant deleteRestaurant(Long id) {
        Restaurant restaurantToDelete = getById(id);
        repository.delete(restaurantToDelete);
        return restaurantToDelete;
    }
}
