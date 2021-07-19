package com.wowls.catalogservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wowls.catalogservice.CatalogEntity;
import com.wowls.catalogservice.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "catalog-topic")
    public void updateQuantity(String kafkaMsg) {
        log.info("kafka msg : " + kafkaMsg);

        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMsg, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e ){
            e.printStackTrace();
        }

        CatalogEntity catalogEntity = catalogRepository.findByProductId((String) map.get("productId"));
        if (catalogEntity != null) {
            catalogEntity.setStock(catalogEntity.getStock() - (Integer) map.get("quantity"));
            catalogRepository.save(catalogEntity);
        }
    }

}
