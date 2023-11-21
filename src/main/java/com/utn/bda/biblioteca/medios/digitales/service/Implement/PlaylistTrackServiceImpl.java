package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.PlaylistTrackDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.PlaylistEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.PlaylistTrackEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.PlaylistTrackRepository;
import com.utn.bda.biblioteca.medios.digitales.service.PlaylistService;
import com.utn.bda.biblioteca.medios.digitales.service.PlaylistTrackService;
import com.utn.bda.biblioteca.medios.digitales.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PlaylistTrackServiceImpl implements PlaylistTrackService {

    private final PlaylistTrackRepository playlistTrackRepository;
    private final PlaylistService playlistService;
    private final TrackService trackService;

    public PlaylistTrackServiceImpl(PlaylistTrackRepository playlistTrackRepository, PlaylistService playlistService, TrackService trackService) {
        this.playlistTrackRepository = playlistTrackRepository;
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

    @Override
    public void addTrackToPlaylist(PlaylistTrackDto playlistTrackDto) {
        this.playlistService.getById(playlistTrackDto.getPlaylistId());
        this.trackService.getById(playlistTrackDto.getTrackId());
        PlaylistTrackEntity playlistEntity = new PlaylistTrackEntity(
                0L,
                PlaylistEntity.builder().id(playlistTrackDto.getPlaylistId()).build(),
                TrackEntity.builder().id(playlistTrackDto.getTrackId()).build()
                );
        this.playlistTrackRepository.save(playlistEntity);

    }

    @Override
    public void deleteTrackFromPlaylist(PlaylistTrackDto playlistTrackDto) {
        this.playlistService.getById(playlistTrackDto.getPlaylistId());
        this.trackService.getById(playlistTrackDto.getTrackId());
        /*
        PlaylistTrackEntity playlistTrackEntity = this.playlistTrackRepository
                .findAll().stream()
                .filter(plt -> plt.getPlaylistEntity().getId().equals(playlistTrackDto.getPlaylistId()) && plt.getTrackEntity().getId().equals(playlistTrackDto.getTrackId()))
                .findFirst().orElseThrow();
         */
        PlaylistTrackEntity playlistTrackEntity = this.playlistTrackRepository
                .findFirstPlaylist(playlistTrackDto.getPlaylistId(), playlistTrackDto.getTrackId())
                .orElseThrow(()-> new NoSuchElementException("El track con id "+playlistTrackDto.getTrackId() + "no se encuentra en la playlist con id "+playlistTrackDto.getPlaylistId()));
        this.playlistTrackRepository.delete(playlistTrackEntity);
    }
}
