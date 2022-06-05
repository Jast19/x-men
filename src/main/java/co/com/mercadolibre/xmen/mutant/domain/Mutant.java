package co.com.mercadolibre.xmen.mutant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class Mutant {

    private String id;
    private String adn;
    private Boolean isMutant;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

}
