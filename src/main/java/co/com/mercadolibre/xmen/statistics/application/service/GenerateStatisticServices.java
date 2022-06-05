package co.com.mercadolibre.xmen.statistics.application.service;

import co.com.mercadolibre.xmen.mutant.domain.Mutant;
import co.com.mercadolibre.xmen.statistics.application.port.in.GenerateStatisticsUseCase;
import co.com.mercadolibre.xmen.statistics.application.port.out.StatisticsQueryPort;
import co.com.mercadolibre.xmen.statistics.domain.Statistics;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GenerateStatisticServices implements GenerateStatisticsUseCase {

    private final StatisticsQueryPort statisticsQueryPort;

    @Autowired
    public GenerateStatisticServices(
        StatisticsQueryPort statisticsQueryPort) {
        this.statisticsQueryPort = statisticsQueryPort;
    }

    @Override
    public Mono<Statistics> statistics() {
        Mono<List<Mutant>> mutantFlux = this.statisticsQueryPort.getMutants();
        Mono<Long> countMutantDna = this.count(mutantFlux, Boolean.TRUE);
        Mono<Long> countHumanDna = this.count(mutantFlux, Boolean.FALSE);

        return countHumanDna.flatMap(countHuman -> countMutantDna
            .map(countMutant -> Statistics.builder()
                .countMutantDna(countMutant)
                .countHumanDna(countHuman)
                .ratio(this.calculateRatio(countMutant, countHuman))
                .build()));
    }

    private BigDecimal calculateRatio(Long countMutantDna, Long countHumanDna) {
        BigDecimal countMutant = BigDecimal.valueOf(countMutantDna);
        BigDecimal countHumant = BigDecimal.valueOf(countHumanDna);
        return countHumanDna == 0L ? BigDecimal.ZERO
            : countMutant.divide(countHumant, 2, RoundingMode.HALF_UP);
    }

    private Mono<Long> count(Mono<List<Mutant>> mutants, Boolean isMutant) {
        return mutants.map(list ->
            list.parallelStream()
                .filter(mutant -> mutant.getIsMutant().equals(isMutant))
                .count()
        );
    }

}
