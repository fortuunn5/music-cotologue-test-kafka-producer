package com.musiccotologue.music_cotologue_api_maven.controller;

import com.musiccotologue.music_cotologue_api_maven.model.Musician;
import com.musiccotologue.music_cotologue_api_maven.repository.musician.MusicianRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/musician")
public class MusicianController {
    private final Logger logger = LoggerFactory.getLogger(MusicianController.class);
    private final MusicianRepository musicianRepository;

    @PostMapping
    public ResponseEntity<Musician> save(@RequestBody Musician musician) {
        Musician save = musicianRepository.save(musician);
        logger.debug("saved {}: {}", save.getName(), save.getId());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Musician>> findAll() {
        List<Musician> all = musicianRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
