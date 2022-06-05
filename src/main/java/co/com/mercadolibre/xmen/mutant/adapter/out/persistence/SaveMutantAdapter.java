package co.com.mercadolibre.xmen.mutant.adapter.out.persistence;

import co.com.mercadolibre.xmen.mutant.application.port.out.SaveMutantPort;
import co.com.mercadolibre.xmen.mutant.domain.Mutant;
import java.util.Arrays;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Slf4j
@Service
public class SaveMutantAdapter implements SaveMutantPort {

    private final String tableName;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Autowired
    public SaveMutantAdapter(@Value("${name.table.mutant}") String tableName,
        DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.tableName = tableName;
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    @Override
    public Mono<Boolean> save(String[] dna, Boolean isMutant) {
        Mutant mutant = Mutant.builder()
            .id(UUID.randomUUID().toString())
            .adn(Arrays.toString(dna))
            .isMutant(isMutant).build();

        return updateItem(mutant).then(Mono.just(isMutant));
    }

    private Mono<Void> updateItem(Mutant item) {
        log.info("Updating element {} in table: {}...", item.toString(), tableName);
        DynamoDbTable<Mutant> mappedTable = this.dynamoDbEnhancedClient.table(tableName,
            TableSchema.fromBean(Mutant.class));
        mappedTable.updateItem(item);
        log.info("Item updated.");
        return Mono.empty().then();
    }

}
