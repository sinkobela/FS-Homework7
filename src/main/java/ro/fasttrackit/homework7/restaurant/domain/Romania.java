package ro.fasttrackit.homework7.restaurant.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("romania")
public class Romania implements CityProvider {

    @Override
    public List<String> getCities() {
        return List.of(
                "Oradea",
                "Cluj",
                "Brasov",
                "Timisoara",
                "Bucuresti",
                "Arad",
                "Tirgu Mures",
                "Constanta",
                "Baia Mare",
                "Iasi"
        );
    }
}
