package co.com.mercadolibre.xmen.mutant.application.service;

import co.com.mercadolibre.xmen.mutant.application.port.in.RecruitMutantUseCase;
import co.com.mercadolibre.xmen.mutant.application.port.out.SaveMutantPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RecruitMutantService implements RecruitMutantUseCase {

    private final VerticalMatch verticalMatch;
    private final SaveMutantPort saveMutantPort;
    private final UtilMatch utilMatch;

    @Autowired
    public RecruitMutantService(VerticalMatch verticalMatch,
        SaveMutantPort saveMutantPort, UtilMatch utilMatch) {
        this.verticalMatch = verticalMatch;
        this.saveMutantPort = saveMutantPort;
        this.utilMatch = utilMatch;
    }

    @Override
    public Mono<Void> recruit(String[] dna) {
        this.utilMatch.validateDna(dna);
        return this.isMutant(dna)
            .flatMap(isMutant -> this.saveMutantPort.save(dna, isMutant))
            .flatMap(this::response);
    }

    @Override
    public Mono<Boolean> isMutant(String[] dna) {
        return this.verticalMatch.match(dna, 0L);
    }

    private Mono<Void> response(Boolean isMutant) {
        if (Boolean.FALSE.equals(isMutant)) {
            return Mono.error(new RecruiteMutantException(HttpStatus.FORBIDDEN));
        }
        return Mono.empty().then();
    }
}
