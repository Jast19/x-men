package co.com.mercadolibre.xmen.mutant.services;

import static org.mockito.BDDMockito.given;

import co.com.mercadolibre.xmen.mutant.application.service.ObliqueMatch;
import co.com.mercadolibre.xmen.mutant.application.service.UtilMatch;
import co.com.mercadolibre.xmen.mutant.data.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ObliqueMatchTest {

    @InjectMocks
    private ObliqueMatch obliqueMatch;

    @Mock
    private UtilMatch utilMatch;

    @Test
    void ObliqueLeftToRightSuccess() {
        String[] dna = MockData.dnaMutantObliqueLeftToRightSuccess();
        given(utilMatch.getNitrogenBaseDna()).willReturn(MockData.getNitrogenBaseDna());
        given(utilMatch.hasMutant(2L)).willReturn(Boolean.TRUE);
        given(utilMatch.patternSizeDna()).willReturn(4);

        Boolean isMutant = obliqueMatch.match(dna, 0L).block();

        Assertions.assertEquals(Boolean.TRUE, isMutant);
    }

    @Test
    void ObliqueRightToLeftSuccess() {
        String[] dna = MockData.dnaMutantObliqueRightToLeftSuccess();
        given(utilMatch.getNitrogenBaseDna()).willReturn(MockData.getNitrogenBaseDna());
        given(utilMatch.hasMutant(2L)).willReturn(Boolean.TRUE);
        given(utilMatch.patternSizeDna()).willReturn(4);

        Boolean isMutant = obliqueMatch.match(dna, 0L).block();

        Assertions.assertEquals(Boolean.TRUE, isMutant);
    }

    @Test
    void ObliqueFail() {
        String[] dna = MockData.dnaMutantObliqueFail();
        given(utilMatch.getNitrogenBaseDna()).willReturn(MockData.getNitrogenBaseDna());
        given(utilMatch.hasMutant(0L)).willReturn(Boolean.FALSE);
        given(utilMatch.patternSizeDna()).willReturn(4);

        Boolean isMutant = obliqueMatch.match(dna, 0L).block();

        Assertions.assertEquals(Boolean.FALSE, isMutant);
    }

}
