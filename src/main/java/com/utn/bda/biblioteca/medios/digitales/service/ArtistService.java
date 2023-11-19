package com.utn.bda.biblioteca.medios.digitales.service;

import com.utn.bda.biblioteca.medios.digitales.model.dto.ArtistDto;

public interface ArtistService extends Service<ArtistDto,Long> {
    boolean exists(long artistId);
}
