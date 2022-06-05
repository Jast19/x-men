package co.com.mercadolibre.xmen.statistics.adapter.in.web;

import co.com.mercadolibre.xmen.statistics.application.port.in.GenerateStatisticsUseCase;
import co.com.mercadolibre.xmen.statistics.domain.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class StatisticController {

    private final GenerateStatisticsUseCase generateStatisticsUseCase;

    @Autowired
    public StatisticController(
        GenerateStatisticsUseCase generateStatisticsUseCase) {
        this.generateStatisticsUseCase = generateStatisticsUseCase;
    }

    @GetMapping("/stats")
    public Mono<Statistics> statistics(){
        return this.generateStatisticsUseCase.statistics();
    }
}
