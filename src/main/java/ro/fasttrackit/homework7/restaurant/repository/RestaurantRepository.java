package ro.fasttrackit.homework7.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.homework7.restaurant.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Page<Restaurant> findAllByStarsIn(List<Integer> stars, Pageable pageable);

    Page<Restaurant> findAllByCityIgnoreCase(String city, Pageable pageable);

    Page<Restaurant> findAllByStarsInAndCityIgnoreCase(List<Integer> stars, String city, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
}
