package com.musiccotologue.music_cotologue_api_maven.repository.musician;

import com.musiccotologue.music_cotologue_api_maven.model.Musician;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@RequiredArgsConstructor
public class MusicianRepositoryImpl implements MusicianRepository {

    private final EntityManager entityManager;

    @Override
    public Musician save(Musician entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Optional<Musician> findById(Long id) {
        Musician musician = entityManager.find(Musician.class, id);
        return Optional.ofNullable(musician);
    }

    @Override
    public List<Musician> findAll() {
        return entityManager.createQuery("from Musician").getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Musician where id = :id")
                .executeUpdate();
    }
}

