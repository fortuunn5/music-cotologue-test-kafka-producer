package com.musiccotologue.music_cotologue_api_maven.controller;

import com.musiccotologue.music_cotologue_api_maven.model.Song;
import com.musiccotologue.music_cotologue_api_maven.repository.song.SongRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/song")
public class SongController {
    private final SongRepository songRepository;
    private final Logger logger = LoggerFactory.getLogger(SongController.class);

    @PostMapping
    public ResponseEntity<Song> save(@RequestBody Song musician) {
        Song save = songRepository.save(musician);
        logger.debug("saved {}: {}", save.getName(), save.getId());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Song>> findAll() {
        List<Song> all = songRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
