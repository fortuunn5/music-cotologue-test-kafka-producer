package com.musiccotologue.music_cotologue_api_maven.repository;

import com.musiccotologue.music_cotologue_api_maven.model.Musician;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MusicianRepository extends BaseRepository<Musician, Long> {
}
