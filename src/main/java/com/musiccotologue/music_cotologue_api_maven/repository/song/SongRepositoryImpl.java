package com.musiccotologue.music_cotologue_api_maven.repository.song;

import com.musiccotologue.music_cotologue_api_maven.kafka.SongKafkaProducer;
import com.musiccotologue.music_cotologue_api_maven.model.Song;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class SongRepositoryImpl implements SongRepository {

    private final EntityManager entityManager;
    // move to service
    private final SongKafkaProducer songKafkaProducer;

    @Override
    public Song save(Song entity) {
        Song merge = entityManager.merge(entity);
        songKafkaProducer.send(merge);
        return merge;
    }

    @Override
    public Optional<Song> findById(Long id) {
        Song musician = entityManager.find(Song.class, id);
        return Optional.ofNullable(musician);
    }

    @Override
    public List<Song> findAll() {
        return entityManager.createQuery("from Song").getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Song where id = :id")
                .executeUpdate();
    }
}
