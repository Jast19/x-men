package co.com.mercadolibre.xmen.mutant.application.service;

import co.com.mercadolibre.xmen.mutant.application.port.in.DetectMutant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ObliqueMatch implements DetectMutant {

    private final UtilMatch utilMatch;

    @Autowired
    public ObliqueMatch(UtilMatch utilMatch) {
        this.utilMatch = utilMatch;
    }

    @Override
    public Mono<Boolean> match(String[] dna, Long initSequence) {
        List<String> sequencesLeftToRight = passDataObliqueLeftToRight(dna);
        long sequence = initSequence
            + this.searchMutant(sequencesLeftToRight, this.utilMatch.getNitrogenBaseDna());

        if (this.utilMatch.hasMutant(sequence)) {
            return Mono.just(Boolean.TRUE);
        }

        return this.obliqueRightToLeft(dna, sequence);
    }

    private Mono<Boolean> obliqueRightToLeft(String[] pattern, Long init) {
        List<String> sequencesRightToLeft = passDataObliqueRightToLeft(pattern);
        long sequence = init + this.searchMutant(sequencesRightToLeft, this.utilMatch.getNitrogenBaseDna());
        return Mono.just(this.utilMatch.hasMutant(sequence));
    }

    private List<String> passDataObliqueLeftToRight(String[] pattern) {
        List<String> leftToRightHigher = this.leftToRightHigher(pattern);
        List<String> leftToRightLower = this.leftToRightLower(pattern);
        leftToRightHigher.addAll(leftToRightLower);
        return leftToRightHigher;
    }

    private List<String> leftToRightHigher(String[] pattern) {
        List<String> sequences = new ArrayList<>();
        for (int i = 0; i < pattern.length; i++) {
            StringBuilder sequenceHigher = new StringBuilder();
            for (int k = 0; k < pattern.length - i; k++) {
                sequenceHigher.append(pattern[k].charAt(k + i));
            }
            if (sequenceHigher.length() < this.utilMatch.patternSizeDna()) {
                break;
            }
            sequences.add(sequenceHigher.toString());
        }
        return sequences;
    }

    private List<String> leftToRightLower(String[] pattern) {
        List<String> sequences = new ArrayList<>();
        for (int i = 1; i < pattern.length; i++) {
            StringBuilder sequenceLower = new StringBuilder();
            for (int k = 0; k < pattern.length - i; k++) {
                sequenceLower.append(pattern[i + k].charAt(k));
            }
            if (sequenceLower.length() < this.utilMatch.patternSizeDna()) {
                break;
            }
            sequences.add(sequenceLower.toString());
        }
        return sequences;
    }

    private List<String> passDataObliqueRightToLeft(String[] pattern) {
        List<String> rightToLeftHigher = this.rightToLeftHigher(pattern);
        List<String> rightToLeftLower = this.rightToLeftLower(pattern);
        rightToLeftHigher.addAll(rightToLeftLower);
        return rightToLeftHigher;
    }

    private List<String> rightToLeftHigher(String[] pattern) {
        List<String> sequences = new ArrayList<>();
        for (int i = 2; i < pattern.length; i++) {
            StringBuilder sequenceHigher = new StringBuilder();
            int j = 0;
            for (int k = pattern.length - i; j <= pattern.length - i; k--, j++) {
                sequenceHigher.append(pattern[j].charAt(k));
            }
            if (sequenceHigher.length() < this.utilMatch.patternSizeDna()) {
                break;
            }
            sequences.add(sequenceHigher.toString());
        }
        return sequences;
    }

    private List<String> rightToLeftLower(String[] pattern) {
        List<String> sequences = new ArrayList<>();
        for (int i = 0; i < pattern.length; i++) {
            StringBuilder sequenceLower = new StringBuilder();
            int j = i;
            for (int k = pattern.length - 1; k >= i; k--) {
                sequenceLower.append(pattern[j].charAt(k));
                j++;
            }
            if (sequenceLower.length() < this.utilMatch.patternSizeDna()) {
                break;
            }
            sequences.add(sequenceLower.toString());
        }
        return sequences;
    }
}
