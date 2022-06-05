package co.com.mercadolibre.xmen.mutant.application.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UtilMatch {

    private final int matchDna;
    private final List<String> nitrogenBaseDna;
    private final String matchLetters;

    @Autowired
    public UtilMatch(@Value("${match.dna}") int matchDna,
        @Value("${nitrogenous.base.dna}") List<String> nitrogenBaseDna,
        @Value("${match.letters}") String matchLetters) {
        this.matchDna = matchDna;
        this.nitrogenBaseDna = nitrogenBaseDna;
        this.matchLetters = matchLetters;
    }

    public void validateDna(String[] dna) {
        int length = dna.length;
        List<String> dnas = Arrays.asList(dna);
        boolean isValidate = dnas.stream().allMatch(d -> d.length() == length);
        if (Boolean.TRUE.equals(isValidate)) {
            this.validateLetters(dnas);
        } else {
            throw new RecruiteMutantException(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean hasMutant(long sequences) {
        return sequences >= this.matchDna;
    }

    public List<String> getNitrogenBaseDna() {
        return this.nitrogenBaseDna;
    }

    public int patternSizeDna() {
        return this.nitrogenBaseDna.get(0).length();
    }

    private void validateLetters(List<String> dnas) {
        dnas.parallelStream().forEach(dna -> {
            boolean match = Pattern.matches(matchLetters, dna);
            if (Boolean.FALSE.equals(match)) {
                throw new RecruiteMutantException(HttpStatus.BAD_REQUEST);
            }
        });
    }

}
