package com.utn.bda.biblioteca.medios.digitales.repository;

import com.utn.bda.biblioteca.medios.digitales.model.entity.PlaylistTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrackEntity,Long> {
    @Query(value = "SELECT plt.* FROM playlist_track plt WHERE plt.playlistid = :playlistId AND plt.trackid = :trackId LIMIT 1", nativeQuery = true)
    Optional<PlaylistTrackEntity> findFirstPlaylist(long playlistId , long trackId);
}
