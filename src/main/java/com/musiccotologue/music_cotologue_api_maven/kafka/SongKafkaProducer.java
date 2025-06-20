package com.musiccotologue.music_cotologue_api_maven.kafka;

import com.musiccotologue.music_cotologue_api_maven.model.Song;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongKafkaProducer {
    private final Logger logger = LoggerFactory.getLogger(SongKafkaProducer.class);
    private final KafkaTemplate<String, Song> kafkaTemplate;

    public void send(Song song) {
        kafkaTemplate.send("songs", song);
        logger.info("Sent song: {}", song);
    }
}
