package co.com.mercadolibre.xmen.mutant.adapter.in.web.handler;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public record ErrorResum(HttpStatus httpStatus, LocalDateTime dateTime, String message) {

}
