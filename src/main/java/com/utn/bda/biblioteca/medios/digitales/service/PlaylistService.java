package com.utn.bda.biblioteca.medios.digitales.service;

import com.utn.bda.biblioteca.medios.digitales.model.dto.PlaylistDto;
import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackDto;

import java.util.List;

public interface PlaylistService extends Service<PlaylistDto,Long>{

    void deleteTrackFromPlaylist( PlaylistDto playlistDto);

    void associationTrackToPlaylist(PlaylistDto playlistDto);

    List<TrackDto> trackListByPlaylist(Long idPlaylist);

    PlaylistDto createPlaylistByArtistAndGenre(long artistId, String playlistName , long genreId , long minutes);
}
