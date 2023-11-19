package com.utn.bda.biblioteca.medios.digitales.service;

import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackByAGDto;
import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackDto;

import java.util.List;

public interface TrackService extends Service<TrackDto,Long>{

    List<TrackByAGDto> getByArtistAndGenre(long artistId ,long genreId);
    public List<TrackDto> getByArtistAndGenreDto(long artistId, long genreId);
}
