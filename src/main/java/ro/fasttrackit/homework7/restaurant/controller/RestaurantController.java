package ro.fasttrackit.homework7.restaurant.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework7.restaurant.domain.Restaurant;
import ro.fasttrackit.homework7.restaurant.service.CollectionResponse;
import ro.fasttrackit.homework7.restaurant.service.PageInfo;
import ro.fasttrackit.homework7.restaurant.service.RestaurantFilters;
import ro.fasttrackit.homework7.restaurant.service.RestaurantService;


@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;


//    @GetMapping
//    public List<Restaurant> getAll(@RequestParam(required = false) List<Integer> stars,
//                                   @RequestParam(required = false) String city) {
//        return service.getAll(stars, city);
//    }

    @GetMapping
    CollectionResponse<Restaurant> getAll(RestaurantFilters filters, Pageable pageable) {
        Page<Restaurant> restaurantPage = service.getAll(filters, pageable);
        return CollectionResponse.<Restaurant>builder()
                .content(restaurantPage.getContent())
                .pageInfo(PageInfo.builder()
                        .totalPages(restaurantPage.getTotalPages())
                        .totalElements(restaurantPage.getNumberOfElements())
                        .crtPage(pageable.getPageNumber())
                        .pageSize(pageable.getPageSize())
                        .build())
                .build();
    }

    @GetMapping("{id}")
    public Restaurant getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.addRestaurant(restaurant);
    }

    @PutMapping("{id}")
    public Restaurant replaceRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return service.replaceRestaurant(id, restaurant);
    }

    @PatchMapping("{id}")
    public Restaurant patchRestaurant(@PathVariable Long id, @RequestBody JsonPatch patch) {
        return service.patchRestaurant(id, patch);
    }

    @DeleteMapping("{id}")
    public Restaurant deleteRestaurant(@PathVariable Long id) {
        return service.deleteRestaurant(id);
    }

}
