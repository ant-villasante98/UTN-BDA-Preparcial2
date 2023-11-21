package com.utn.bda.biblioteca.medios.digitales.service;

import com.utn.bda.biblioteca.medios.digitales.model.dto.PlaylistTrackDto;

public interface PlaylistTrackService {
    void addTrackToPlaylist(PlaylistTrackDto playlistTrackDto);
    void deleteTrackFromPlaylist(PlaylistTrackDto playlistTrackDto);

}
