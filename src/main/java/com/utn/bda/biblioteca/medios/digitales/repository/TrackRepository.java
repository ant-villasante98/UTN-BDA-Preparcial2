package com.utn.bda.biblioteca.medios.digitales.repository;

import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackRepository extends JpaRepository<TrackEntity,Long> {
    @Query(value = "SELECT tr.* FROM tracks tr INNER JOIN albums al ON al.AlbumId = tr.AlbumId  WHERE al.ArtistId = :artistId AND tr.GenreId = :genreId", nativeQuery = true)
    List<TrackEntity> findByArtistAndGenre(long artistId, long genreId);

    //@Query(value = "SELECT tr.* FROM tracks tr INNER JOIN artists ar ON ar.artistsid = tr.trackid")
}
