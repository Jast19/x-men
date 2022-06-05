package co.com.mercadolibre.xmen.mutant.services;


import static org.mockito.BDDMockito.given;

import co.com.mercadolibre.xmen.mutant.application.service.HorizontalMatch;
import co.com.mercadolibre.xmen.mutant.application.service.UtilMatch;
import co.com.mercadolibre.xmen.mutant.application.service.VerticalMatch;
import co.com.mercadolibre.xmen.mutant.data.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class VerticalMatchTest {

    @InjectMocks
    private VerticalMatch verticalMatch;

    @Mock
    private UtilMatch utilMatch;

    @Mock
    private HorizontalMatch horizontalMatch;

    @Test
    void VerticalMatchSuccess() {
        String[] dna = MockData.dnaMutantVerticalSucess();
        given(utilMatch.getNitrogenBaseDna()).willReturn(MockData.getNitrogenBaseDna());
        given(utilMatch.hasMutant(5L)).willReturn(Boolean.TRUE);

        Boolean isMutant = verticalMatch.match(dna, 0L).block();

        Assertions.assertEquals(Boolean.TRUE, isMutant);
    }

    @Test
    void VerticalMatchFail() {
        String[] dna = MockData.dnaMutantVerticalFail();
        given(utilMatch.getNitrogenBaseDna()).willReturn(MockData.getNitrogenBaseDna());
        given(utilMatch.hasMutant(1L)).willReturn(Boolean.FALSE);
        given(horizontalMatch.match(dna, 1L)).willReturn(Mono.just(Boolean.FALSE));

        Boolean isMutant = verticalMatch.match(dna, 0L).block();

        Assertions.assertEquals(Boolean.FALSE, isMutant);
    }


}
