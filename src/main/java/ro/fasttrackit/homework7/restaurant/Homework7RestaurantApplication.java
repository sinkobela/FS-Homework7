package ro.fasttrackit.homework7.restaurant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.homework7.restaurant.domain.Restaurant;
import ro.fasttrackit.homework7.restaurant.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Homework7RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(Homework7RestaurantApplication.class, args);
	}

	@Bean
	CommandLineRunner atStartup(RestaurantRepository repository) {
		return args ->
				repository.saveAll(List.of(
						new Restaurant("Corsarul", 4, "Oradea", LocalDate.of(2000,1,1)),
						new Restaurant("Crinul Alb", 5, "Cluj", LocalDate.of(2001,2,1)),
						new Restaurant("McDonald's", 3, "Arad", LocalDate.of(2002,3,1)),
						new Restaurant("Pizza Hut", 4, "Timisoara", LocalDate.of(2003,4,1)),
						new Restaurant("KFC", 5, "Brasov", LocalDate.of(2004,5,1)),
						new Restaurant("Allegria", 2, "Bucuresti", LocalDate.of(2005,6,1)),
						new Restaurant("Spoon", 1, "Tirgu Mures", LocalDate.of(2006,7,1)),
						new Restaurant("Via69", 5, "Constanta", LocalDate.of(2007,8,1)),
						new Restaurant("Due Fratelli", 3, "Iasi", LocalDate.of(2008,9,1)),
						new Restaurant("Rustic", 2, "Baia Mare", LocalDate.of(2009,10,1))
				));
	}

}
