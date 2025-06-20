package com.musiccotologue.music_cotologue_api_maven.repository;

import com.musiccotologue.music_cotologue_api_maven.model.Song;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class SongRepositoryImpl implements SongRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Song save(Song entity) {
        return sessionFactory.getCurrentSession()
                .merge(entity);
    }

    @Override
    public Optional<Song> findById(Long id) {
        Song musician = sessionFactory.getCurrentSession()
                .get(Song.class, id);
        return Optional.ofNullable(musician);
    }

    @Override
    public List<Song> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Song").list();
    }

    @Override
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Song where id = :id")
                .executeUpdate();
    }
}
