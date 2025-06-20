package com.musiccotologue.music_cotologue_api_maven.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musiccotologue.music_cotologue_api_maven.model.Song;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.host}")
    private String KAFKA_HOST;

    @Bean
    public ProducerFactory<String, Song> kafkaProducer(ObjectMapper objectMapper) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_HOST);

        JsonSerializer<Song> jsonSerializer = new JsonSerializer<>(objectMapper);
        jsonSerializer.setAddTypeInfo(true);
        return new DefaultKafkaProducerFactory<>(props, new StringSerializer(), jsonSerializer);
    }

    @Bean
    public KafkaTemplate<String, Song> kafkaTemplate(ProducerFactory<String, Song> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
