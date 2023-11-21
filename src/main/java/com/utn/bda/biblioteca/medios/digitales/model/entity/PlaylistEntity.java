package com.utn.bda.biblioteca.medios.digitales.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlistid")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "playlistEntity")
    private List<PlaylistTrackEntity> playlistTrackEntities;

    /*
    @ManyToMany
    @JoinTable(
            name = "playlist_track",
            joinColumns = @JoinColumn(name = "playlistid"),
            inverseJoinColumns = @JoinColumn(name = "trackid")
    )
    private List<TrackEntity> playlistTrack;

    */

}
