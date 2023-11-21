package com.utn.bda.biblioteca.medios.digitales.service;

public interface PlaylistTrackService {
    void addTrackToPlaylist(Long trackId, Long playlistId);
    void deleteTrackFromPlaylist(Long trackId, Long playlistId);

}
