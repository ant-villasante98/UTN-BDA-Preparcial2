package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.ArtistDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.*;
import com.utn.bda.biblioteca.medios.digitales.repository.ArtistRepository;
import com.utn.bda.biblioteca.medios.digitales.service.ArtistService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.ArtistMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistServiceImplTest {
    @InjectMocks
    private ArtistService artistService ;
    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ArtistMapper artistMapper;


    @Test
    void testGetAll() {
        List<ArtistEntity> artistEntities = List.of(
                new ArtistEntity(1, "AC/DC", Collections.emptyList()),
                new ArtistEntity(2, "John Mayer", Collections.emptyList()),
                new ArtistEntity(3 , "Ryusenkey", Collections.emptyList())
        );
        Mockito.when(artistRepository.findAll()).thenReturn(artistEntities);
        List<ArtistDto> artistDtos = this.artistService.getAll();
        Assertions.assertEquals(artistDtos.size() , 3);
    }

    @Test
    void getById() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void exists() {
    }
}