package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackByAGDto;
import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.TrackRepository;
import com.utn.bda.biblioteca.medios.digitales.service.ArtistService;
import com.utn.bda.biblioteca.medios.digitales.service.TrackService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.TrackMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;
    private final ArtistService artistService;
    public TrackServiceImpl(TrackRepository trackRepository, TrackMapper trackMapper, ArtistService artistService) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
        this.artistService = artistService;
    }

    @Override
    public List<TrackDto> getAll() {
        List<TrackEntity> trackEntities = this.trackRepository.findAll();
        return trackEntities.stream()
                .map(trackMapper::toDto)
                .toList();
    }

    @Override
    public TrackDto getById(Long id) {
        Optional<TrackEntity> optionalTrack = this.trackRepository.findById(id);
        return optionalTrack
                .map(trackMapper::toDto)
                .orElseThrow();
    }

    @Override
    public TrackDto save(TrackDto model) {
        Optional<TrackEntity> optionalTrack = Stream.of(model).map(trackMapper::toEntity).findFirst();
        if (optionalTrack.get().getId() == null){
            Optional<Long> maxId = this.trackRepository.findAll().stream().map(TrackEntity::getId).max(Long::compareTo);
            maxId.ifPresent(id -> optionalTrack.get().setId(id+1));
        }
        TrackEntity trackEntity = optionalTrack.orElseThrow();
        this.trackRepository.save(trackEntity);
        return Stream.of(trackEntity).map(trackMapper::toDto).findFirst().orElseThrow();
    }

    @Override
    public void delete(Long id) {

        Optional<TrackEntity> optionalTrack = this.trackRepository.findById(id);
        this.trackRepository.delete(optionalTrack.orElseThrow());
    }


    @Override
    public List<TrackByAGDto> getByArtistAndGenre(long artistId, long genreId) {
        boolean existeArtist = this.artistService.exists(artistId);
        if (!existeArtist){
            // Exception para cuando el elemento no existe
            throw new NoSuchElementException();
        }

        List<TrackEntity> trackEntities = this.trackRepository.findByArtistAndGenre(artistId,genreId);
        if (trackEntities.isEmpty()){
            // Exception par cuendo la lista esta vacia
            throw new NullPointerException();
        }
        return trackEntities.stream()
                .map(TrackByAGDto::from)
                .toList();
    }

    @Override
    public List<TrackDto> getByArtistAndGenreDto(long artistId, long genreId) {
        boolean existeArtist = this.artistService.exists(artistId);
        if (!existeArtist){
            // Exception para cuando el elemento no existe
            throw new NoSuchElementException();
        }

        List<TrackEntity> trackEntities = this.trackRepository.findByArtistAndGenre(artistId,genreId);
        if (trackEntities.isEmpty()){
            // Exception par cuendo la lista esta vacia
            throw new NullPointerException();
        }
        return trackEntities.stream()
                .map(trackMapper::toDto)
                .toList();
    }
}
