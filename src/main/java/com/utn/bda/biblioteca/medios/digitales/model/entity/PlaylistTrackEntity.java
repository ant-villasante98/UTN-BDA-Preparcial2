package com.utn.bda.biblioteca.medios.digitales.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "playlist_track")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlisttrackid")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "playlistid",
            referencedColumnName = "playlistid"
    )
    private PlaylistEntity playlistEntity;

    @ManyToOne
    @JoinColumn(
            name = "trackid",
            referencedColumnName = "trackid"
    )
    private TrackEntity trackEntity;
}
