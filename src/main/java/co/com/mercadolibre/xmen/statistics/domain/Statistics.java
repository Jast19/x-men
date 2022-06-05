package co.com.mercadolibre.xmen.statistics.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Statistics {

    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;

    @JsonProperty("count_human_dna")
    private Long countHumanDna;
    private BigDecimal ratio;

}
