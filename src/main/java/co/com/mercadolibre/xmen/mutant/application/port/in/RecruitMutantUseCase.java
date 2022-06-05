package co.com.mercadolibre.xmen.mutant.application.port.in;

import reactor.core.publisher.Mono;

public interface RecruitMutantUseCase {

    Mono<Void> recruit(String[] dna);

    Mono<Boolean> isMutant(String[] dna);

}
