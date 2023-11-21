package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.PlaylistDto;
import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.PlaylistEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.PlaylistRepository;
import com.utn.bda.biblioteca.medios.digitales.repository.TrackRepository;
import com.utn.bda.biblioteca.medios.digitales.service.PlaylistService;
import com.utn.bda.biblioteca.medios.digitales.service.TrackService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.PlaylistMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final TrackService trackService;
    public PlaylistServiceImpl(PlaylistRepository playlistRepository, PlaylistMapper playlistMapper, TrackService trackService) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
        this.trackService = trackService;
    }

    @Override
    public List<PlaylistDto> getAll() {
        List<PlaylistEntity> playlistEntities = this.playlistRepository.findAll();
        return playlistEntities.stream()
                .map(playlistMapper::toDto)
                .toList();
    }

    @Override
    public PlaylistDto getById(Long id) {
        Optional<PlaylistEntity> optionalPlaylist = this.playlistRepository.findById(id);
        return optionalPlaylist.map(playlistMapper::toDto).orElseThrow(()-> new NoSuchElementException("Playlist no encontrada"));
    }

    @Override
    public PlaylistDto add(PlaylistDto model) {
        PlaylistEntity playlist = this.playlistMapper.toEntity(model);
        PlaylistEntity playlistEntitySaved = this.playlistRepository.save(playlist);
        return this.playlistMapper.toDto(playlistEntitySaved);
    }

    @Override
    public void update(Long id, PlaylistDto model) {
        PlaylistEntity playlistEntity = this.playlistRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Playlist no encontrada"));
        playlistEntity.setName(model.getName());
        this.playlistRepository.save(playlistEntity);
    }

    @Override
    public void delete(Long id) {
        PlaylistEntity playlist = this.playlistRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Playlist no encontrada"));
        this.playlistRepository.delete(playlist);

    }
/*
    @Override
    public void deleteTrackFromPlaylist( PlaylistDto playlistDto) {
        Optional<PlaylistEntity> playlistEntity = this.playlistRepository.findById(playlistDto.getId());
        if(playlistEntity.isPresent()) {
            for (TrackDto track : playlistDto.getTrackList()) {
                Optional<TrackEntity> optionaltrackEntity = this.trackRepository.findById(track.getId());
                TrackEntity trackEntity = optionaltrackEntity.orElseThrow();
                trackEntity.deletePlaylistAssociation(playlistEntity.orElseThrow());
                this.trackRepository.save(trackEntity);
            }
        }
    }

    @Override
    public void associationTrackToPlaylist(PlaylistDto playlistDto) {
        Optional<PlaylistEntity> playlistEntity = this.playlistRepository.findById(playlistDto.getId());
        if(playlistEntity.isPresent()) {
            //
            // Hay problema de PK cuando el track ya existe en la asociacion con la playlist
            for (TrackDto track : playlistDto.getTrackList()) {
                Optional<TrackEntity> optionaltrackEntity = this.trackRepository.findById(track.getId());
                TrackEntity trackEntity = optionaltrackEntity.orElseThrow();
                trackEntity.addPlaylistAssociation(playlistEntity.orElseThrow());
                this.trackRepository.save(trackEntity);
            }
        }
    }

 */

    @Override
    public List<TrackDto> trackListByPlaylist(Long idPlaylist) {
        PlaylistDto playlistDto = this.getById(idPlaylist);

        return playlistDto.getTrackList();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public PlaylistDto createPlaylistByArtistAndGenre(long artistId, String playlistName, long genreId, long minutes) {

        List<TrackDto> trackList = this.trackService.getByArtistAndGenreDto(artistId,genreId);
        List<TrackDto> trackDtos = new java.util.ArrayList<>(Collections.emptyList());

        long milisegundosRequerido = minutes*60*1000;
        long contador =0;

        for (TrackDto track : trackList){
            contador += track.getMilliseconds();
            if (contador<= milisegundosRequerido){

                trackDtos.add(track);
            }
        }

        PlaylistDto playlistDto = new PlaylistDto(null,playlistName,trackDtos);


        PlaylistDto playlistDtoSaved = this.add(playlistDto);
        List<TrackDto> tracksOrdenadas = playlistDtoSaved.
                getTrackList().stream()
                .sorted(
                    Comparator.comparing(TrackDto::getBytes).reversed()
                )
                .toList();

        playlistDtoSaved.setTrackList(tracksOrdenadas);
        return  playlistDtoSaved;
    }


}
