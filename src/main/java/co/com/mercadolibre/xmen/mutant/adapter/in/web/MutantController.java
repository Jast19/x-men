package co.com.mercadolibre.xmen.mutant.adapter.in.web;

import co.com.mercadolibre.xmen.mutant.adapter.in.web.dto.Request;
import co.com.mercadolibre.xmen.mutant.application.port.in.RecruitMutantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class MutantController {

    private final RecruitMutantUseCase recruitMutantUseCase;

    @Autowired
    public MutantController(
        RecruitMutantUseCase recruitMutantUseCase) {
        this.recruitMutantUseCase = recruitMutantUseCase;
    }


    @PostMapping("/mutant")
    public Mono<Void> mutant(@RequestBody Request request) {
        return recruitMutantUseCase.recruit(request.getDna());
    }
}
