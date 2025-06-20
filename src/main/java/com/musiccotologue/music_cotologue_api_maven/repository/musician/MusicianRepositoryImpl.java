package com.musiccotologue.music_cotologue_api_maven.repository;

import com.musiccotologue.music_cotologue_api_maven.model.Musician;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@RequiredArgsConstructor
public class MusicianRepositoryImpl implements MusicianRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Musician save(Musician entity) {
        return sessionFactory.getCurrentSession()
                .merge(entity);
    }

    @Override
    public Optional<Musician> findById(Long id) {
        Musician musician = sessionFactory.getCurrentSession()
                .get(Musician.class, id);
        return Optional.ofNullable(musician);
    }

    @Override
    public List<Musician> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Musician").list();
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Musician where id = :id")
                .executeUpdate();
    }
}

