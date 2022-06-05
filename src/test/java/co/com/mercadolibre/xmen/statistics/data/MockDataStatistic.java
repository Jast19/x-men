package co.com.mercadolibre.xmen.statistics.data;

import co.com.mercadolibre.xmen.mutant.domain.Mutant;
import java.util.Arrays;
import java.util.List;
import reactor.core.publisher.Mono;

public class MockDataStatistic {

    public static Mono<List<Mutant>> listMono(){
        List<Mutant> mutants = Arrays.asList(
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(false).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(false).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(false).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(true).build(),
            Mutant.builder().id("1").adn("adn").isMutant(false).build()
        );
        return Mono.just(mutants);
    }

}
