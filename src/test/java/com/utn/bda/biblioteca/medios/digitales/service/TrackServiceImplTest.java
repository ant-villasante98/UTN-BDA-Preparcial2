package com.utn.bda.biblioteca.medios.digitales.service;

import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.AlbumEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.GenreEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.MediaTypeEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.TrackRepository;
import com.utn.bda.biblioteca.medios.digitales.service.Implement.TrackServiceImpl;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.TrackMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

class TrackServiceImplTest {
    private TrackServiceImpl trackService;
   private TrackMapper trackMapper;
   private TrackRepository trackRepositoryMock;


    @BeforeEach
    public void after(){
        trackRepositoryMock = Mockito.mock(TrackRepository.class);
        trackMapper = new TrackMapper();
        //trackService = new TrackServiceImpl(trackRepositoryMock,trackMapper);

    }

    @Test
    public void getByIdTest(){
        TrackEntity track = new TrackEntity(
                10L,
                "Rosie",
                "John Mayer",
                128000L,
                9L,
                2.5F,
                Collections.emptyList(),
                AlbumEntity.builder().id(2L).build(),
                GenreEntity.builder().id(1L).build(),
                MediaTypeEntity.builder().id(2L).build(),
                Collections.emptyList()
        );

        Mockito.when(trackRepositoryMock.findById(10L)).thenReturn(Optional.of(track));
        TrackDto trackDto = this.trackService.getById(10L);
        Assertions.assertEquals(trackDto , trackMapper.toDto(track));
    }

}