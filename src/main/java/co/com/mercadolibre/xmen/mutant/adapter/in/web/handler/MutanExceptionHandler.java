package co.com.mercadolibre.xmen.mutant.adapter.in.web.handler;

import co.com.mercadolibre.xmen.mutant.application.service.RecruiteMutantException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MutanExceptionHandler {

    @ExceptionHandler(RecruiteMutantException.class)
    public ResponseEntity<ErrorResum> recruiteMutantException(RecruiteMutantException ex) {
        ErrorResum errorResum = new ErrorResum(ex.getHttpStatus(), LocalDateTime.now(),
            ex.getHttpStatus().getReasonPhrase());
        log.error(" httpStatus :: {} ", errorResum.httpStatus());
        log.error(" Message :: {}", ex.getMessage());
        return new ResponseEntity<>(errorResum, ex.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResum> handler(RuntimeException ex) {
        ErrorResum errorResum = new ErrorResum(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error(" Error :: {}, {} ", ex.getMessage(), ex);
        return new ResponseEntity<>(errorResum, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
