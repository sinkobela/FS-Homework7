package ro.fasttrackit.homework7.restaurant.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("hungary")
public class Hungary implements CityProvider {

    @Override
    public List<String> getCities() {
        return List.of(
                "Budapest",
                "Debrecen",
                "Szeged",
                "Bugyi",
                "Pecs",
                "Nyiregyhaza"
        );
    }
}
