package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackByAGDto;
import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.AlbumEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.GenreEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.MediaTypeEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.TrackRepository;
import com.utn.bda.biblioteca.medios.digitales.service.*;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.TrackMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final GenreService genreService;
    private final MediaTypeService mediaTypeService;
    public TrackServiceImpl(TrackRepository trackRepository, TrackMapper trackMapper, ArtistService artistService, AlbumService albumService, GenreService genreService, MediaTypeService mediaTypeService) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
        this.artistService = artistService;
        this.albumService = albumService;
        this.genreService = genreService;
        this.mediaTypeService = mediaTypeService;
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
                .orElseThrow(()-> new NoSuchElementException("Track no encontrado con el id "+id));
    }

    @Override
    public TrackDto add(TrackDto model) {
        TrackEntity trackEntity = this.trackMapper.toEntity(model);
        this.albumService.getById(model.getAlbumId());
        this.genreService.getById(model.getGenreId());
        this.mediaTypeService.getById(model.getMediaTypeId());
        TrackEntity savedTrackEntity = this.trackRepository.save(trackEntity);
        return this.trackMapper.toDto(savedTrackEntity);
    }

    @Override
    public void update(Long id, TrackDto model) {
        TrackEntity trackEntity = this.trackRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Track no encontrado con el id "+id));
        this.albumService.getById(model.getAlbumId());
        this.genreService.getById(model.getGenreId());
        this.mediaTypeService.getById(model.getMediaTypeId());
        trackEntity.setName(model.getName());
        trackEntity.setComposer(model.getComposer());
        trackEntity.setMilliseconds(model.getMilliseconds());
        trackEntity.setBytes(model.getBytes());
        trackEntity.setUnitPrice(model.getUnitPrice());
        trackEntity.setAlbumEntity(AlbumEntity.builder().id(model.getAlbumId()).build());
        trackEntity.setGenreEntity(GenreEntity.builder().id(model.getGenreId()).build());
        trackEntity.setMediaTypeEntity(MediaTypeEntity.builder().id(model.getMediaTypeId()).build());

        this.trackRepository.save(trackEntity);

    }

    @Override
    public void delete(Long id) {

        Optional<TrackEntity> optionalTrack = this.trackRepository.findById(id);
        this.trackRepository.delete(optionalTrack.orElseThrow(()-> new NoSuchElementException("Track no encontrado con el id "+id)));
    }


    //Consigna 1
    @Override
    public List<TrackByAGDto> getByArtistAndGenre(long artistId, long genreId) {
        boolean existeArtist = this.artistService.exists(artistId);
        if (!existeArtist){
            // Exception para cuando el elemento no existe
            throw new NoSuchElementException();
        }

        //Opcion 1
        //List<TrackEntity> trackEntities = this.trackRepository.findByArtistAndGenre(artistId,genreId);
        //Opcion 2
        List<TrackEntity> trackEntities = this.trackRepository.findAll().stream()
                .filter(track-> track.getAlbumEntity().getArtistEntity().getId()==artistId && track.getGenreEntity().getId()==genreId)
                .toList();
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
