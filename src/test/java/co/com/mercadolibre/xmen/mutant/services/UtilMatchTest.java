package co.com.mercadolibre.xmen.mutant.services;

import co.com.mercadolibre.xmen.mutant.application.service.RecruiteMutantException;
import co.com.mercadolibre.xmen.mutant.application.service.UtilMatch;
import co.com.mercadolibre.xmen.mutant.data.MockData;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtilMatchTest {

    @Autowired
    private UtilMatch utilMatch;


    @Test
    void validateDnaSucess() {
        String[] dna = MockData.dnaHuman();
        utilMatch.validateDna(dna);
        Assertions.assertTrue(true);
    }

    @Test
    void validateDnaLengthFail() {
        String[] dna = MockData.dnaHumanLengthFail();
        try {
            utilMatch.validateDna(dna);
        } catch (RecruiteMutantException exception){
            Assertions.assertEquals(400, exception.getHttpStatus().value());
        }
    }

    @Test
    void validateDnaLetterFail() {
        String[] dna = MockData.dnaHumanLetterFail();
        try {
            utilMatch.validateDna(dna);
        } catch (RecruiteMutantException exception){
            Assertions.assertEquals(400, exception.getHttpStatus().value());
        }
    }

    @Test
    void hasMutantTrue() {
        long sequences = 2L;
        Boolean response = utilMatch.hasMutant(sequences);
        Assertions.assertEquals(Boolean.TRUE, response);
    }

    @Test
    void ListNitrogenBaseDna() {
        List<String> getNitrogenBaseDna = utilMatch.getNitrogenBaseDna();
        Assertions.assertEquals(4, getNitrogenBaseDna.size());
    }

    @Test
    void patternSizeDna() {
        int patternSize = utilMatch.patternSizeDna();
        Assertions.assertEquals(4, patternSize);
    }

}
