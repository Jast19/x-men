package co.com.mercadolibre.xmen.mutant.application.service;

import co.com.mercadolibre.xmen.mutant.application.port.in.DetectMutant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class HorizontalMatch implements DetectMutant {

    private final UtilMatch utilMatch;
    private final ObliqueMatch obliqueMatch;

    @Autowired
    public HorizontalMatch(UtilMatch utilMatch, ObliqueMatch obliqueMatch) {
        this.utilMatch = utilMatch;
        this.obliqueMatch = obliqueMatch;
    }

    @Override
    public Mono<Boolean> match(String[] dna, Long initSequence) {
        List<String> sequences = this.horizontalDna(dna);

        long sequence = initSequence
            + this.searchMutant(sequences, this.utilMatch.getNitrogenBaseDna());

        return this.utilMatch.hasMutant(sequence) ? Mono.just(Boolean.TRUE)
            : this.obliqueMatch.match(dna, sequence);
    }

    private List<String> horizontalDna(String[] pattern) {
        List<String> sequences = new ArrayList<>();
        for (int i = 0; i < pattern.length; i++) {
            StringBuilder sequence = new StringBuilder();
            for (String s : pattern) {
                sequence.append(s.charAt(i));
            }
            sequences.add(sequence.toString());
        }
        return sequences;
    }
}
