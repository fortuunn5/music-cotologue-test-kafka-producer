package com.musiccotologue.music_cotologue_api_maven.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "vc_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "musician_id")
    private Musician musician;

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
