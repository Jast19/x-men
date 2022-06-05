package co.com.mercadolibre.xmen.mutant.services;

import static org.mockito.BDDMockito.given;

import co.com.mercadolibre.xmen.mutant.application.service.HorizontalMatch;
import co.com.mercadolibre.xmen.mutant.application.service.ObliqueMatch;
import co.com.mercadolibre.xmen.mutant.application.service.UtilMatch;
import co.com.mercadolibre.xmen.mutant.data.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class HorizontalMatchTest {

    @InjectMocks
    private HorizontalMatch horizontalMatch;

    @Mock
    private UtilMatch utilMatch;

    @Mock
    private ObliqueMatch obliqueMatch;

    @Test
    void horizontalMatchSuccess() {
        String[] dna = MockData.dnaMutantHorizontalSuccess();
        given(utilMatch.getNitrogenBaseDna()).willReturn(MockData.getNitrogenBaseDna());
        given(utilMatch.hasMutant(2L)).willReturn(Boolean.TRUE);

        Boolean isMutant = horizontalMatch.match(dna, 0L).block();

        Assertions.assertEquals(Boolean.TRUE, isMutant);
    }

    @Test
    void horizontalMatchFail() {
        String[] dna = MockData.dnaMutantHorizontalFail();
        given(utilMatch.getNitrogenBaseDna()).willReturn(MockData.getNitrogenBaseDna());
        given(utilMatch.hasMutant(1L)).willReturn(Boolean.FALSE);
        given(obliqueMatch.match(dna, 1L)).willReturn(Mono.just(Boolean.FALSE));

        Boolean isMutant = horizontalMatch.match(dna, 0L).block();

        Assertions.assertEquals(Boolean.FALSE, isMutant);
    }

}
