package co.com.mercadolibre.xmen.statistics.application.port.in;

import co.com.mercadolibre.xmen.statistics.domain.Statistics;
import reactor.core.publisher.Mono;

public interface GenerateStatisticsUseCase {

    Mono<Statistics> statistics();

}
