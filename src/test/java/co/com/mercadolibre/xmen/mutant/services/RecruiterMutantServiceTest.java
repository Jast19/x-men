package co.com.mercadolibre.xmen.mutant.services;

import co.com.mercadolibre.xmen.mutant.application.port.out.SaveMutantPort;
import co.com.mercadolibre.xmen.mutant.application.service.RecruitMutantService;
import co.com.mercadolibre.xmen.mutant.application.service.RecruiteMutantException;
import co.com.mercadolibre.xmen.mutant.application.service.UtilMatch;
import co.com.mercadolibre.xmen.mutant.application.service.VerticalMatch;
import co.com.mercadolibre.xmen.mutant.data.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class RecruiterMutantServiceTest {

    @InjectMocks
    private RecruitMutantService recruitMutantService;

    @Mock
    private UtilMatch utilMatch;

    @Mock
    private SaveMutantPort saveMutantPort;

    @Mock
    private VerticalMatch verticalMatch;


    @Test
    void isMutant() {
        String[] dna = MockData.dnaHuman();

        Mockito.doNothing().when(this.utilMatch).validateDna(dna);
        Mockito.doReturn(Mono.just(Boolean.TRUE)).when(this.saveMutantPort).save(dna, Boolean.TRUE);
        Mockito.doReturn(Mono.just(Boolean.TRUE)).when(this.verticalMatch).match(dna, 0L);

        StepVerifier.create(this.recruitMutantService.recruit(dna))
            .expectNextCount(0)
            .verifyComplete();

    }

    @Test
    void isHuman() {
        String[] dna = MockData.dnaHuman();

        Mockito.doNothing().when(this.utilMatch).validateDna(dna);
        Mockito.doReturn(Mono.just(Boolean.FALSE)).when(this.saveMutantPort).save(dna, Boolean.FALSE);
        Mockito.doReturn(Mono.just(Boolean.FALSE)).when(this.verticalMatch).match(dna, 0L);

        StepVerifier.create(this.recruitMutantService.recruit(dna))
            .expectErrorMatches(throwable -> throwable instanceof RecruiteMutantException ex
                && ex.getHttpStatus().value() == 403)
            .verify();
    }

}
