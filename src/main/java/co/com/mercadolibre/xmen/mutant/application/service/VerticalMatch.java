package co.com.mercadolibre.xmen.mutant.application.service;

import co.com.mercadolibre.xmen.mutant.application.port.in.DetectMutant;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VerticalMatch implements DetectMutant {

    private final UtilMatch utilMatch;
    private final HorizontalMatch horizontalMatch;


    @Autowired
    public VerticalMatch(UtilMatch utilMatch, HorizontalMatch horizontalMatch) {
        this.utilMatch = utilMatch;
        this.horizontalMatch = horizontalMatch;
    }

    @Override
    public Mono<Boolean> match(String[] dna, Long initSequence) {
        List<String> sequenceADN = Arrays.asList(dna);
        long sequence = initSequence
            + this.searchMutant(sequenceADN, this.utilMatch.getNitrogenBaseDna());

        return this.utilMatch.hasMutant(sequence) ? Mono.just(Boolean.TRUE)
            : this.horizontalMatch.match(dna, sequence);



    }

}
