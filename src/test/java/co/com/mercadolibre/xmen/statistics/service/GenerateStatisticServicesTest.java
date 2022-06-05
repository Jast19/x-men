package co.com.mercadolibre.xmen.statistics.service;

import co.com.mercadolibre.xmen.mutant.domain.Mutant;
import co.com.mercadolibre.xmen.statistics.application.port.out.StatisticsQueryPort;
import co.com.mercadolibre.xmen.statistics.application.service.GenerateStatisticServices;
import co.com.mercadolibre.xmen.statistics.data.MockDataStatistic;
import co.com.mercadolibre.xmen.statistics.domain.Statistics;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class GenerateStatisticServicesTest {

    @InjectMocks
    private GenerateStatisticServices generateStatisticServices;

    @Mock
    private StatisticsQueryPort statisticsQueryPort;

    @Test
    void statisticTest() {
        Mono<List<Mutant>> mutantFlux = MockDataStatistic.listMono();

        Mockito.doReturn(mutantFlux).when(this.statisticsQueryPort).getMutants();

        Statistics statistics = this.generateStatisticServices.statistics().block();

        assert statistics != null;
        Assertions.assertEquals(11L, statistics.getCountMutantDna());
        Assertions.assertEquals(4L, statistics.getCountHumanDna());
        Assertions.assertEquals(BigDecimal.valueOf(2.7500), statistics.getRatio());

    }
}
