package co.com.mercadolibre.xmen.mutant.application.port.out;

import reactor.core.publisher.Mono;

public interface SaveMutantPort {

    Mono<Boolean> save(String[] dna, Boolean isMutant);

}
