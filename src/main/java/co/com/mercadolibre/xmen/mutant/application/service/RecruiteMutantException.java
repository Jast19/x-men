package co.com.mercadolibre.xmen.mutant.application.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RecruiteMutantException extends RuntimeException {

    private final HttpStatus httpStatus;

    public RecruiteMutantException(HttpStatus httpStatus) {
        super(httpStatus.name());
        this.httpStatus = httpStatus;
    }

}
