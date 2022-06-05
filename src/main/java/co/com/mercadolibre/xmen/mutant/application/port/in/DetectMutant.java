package co.com.mercadolibre.xmen.mutant.application.port.in;

import java.util.List;
import reactor.core.publisher.Mono;

public interface DetectMutant {

    Mono<Boolean> match(String[] dna, Long initSequence);

    default long searchMutant(List<String> sequenceADN, List<String> nitrogenBaseADN) {
        return sequenceADN.stream()
            .filter(sequence -> nitrogenBaseADN.stream().anyMatch(sequence::contains))
            .count();
    }

}
