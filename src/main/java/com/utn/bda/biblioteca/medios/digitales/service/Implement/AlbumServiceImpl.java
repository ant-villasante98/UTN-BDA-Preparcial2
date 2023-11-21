package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.AlbumDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.AlbumEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.ArtistEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.AlbumRepository;
import com.utn.bda.biblioteca.medios.digitales.service.AlbumService;
import com.utn.bda.biblioteca.medios.digitales.service.ArtistService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.AlbumMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final ArtistService artistService;
    private final AlbumMapper albumMapper;
    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistService artistService, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
        this.albumMapper = albumMapper;
    }

    @Override
    public List<AlbumDto> getAll() {
        List<AlbumEntity> albumEntities = this.albumRepository.findAll();
        return albumEntities.stream()
                .map(albumMapper::toDto)
                .toList()
                ;
    }

    @Override
    public AlbumDto getById(Long id) {
        Optional<AlbumEntity> optionalAlbum = this.albumRepository.findById(id);
        return optionalAlbum.map(albumMapper::toDto).orElseThrow(()-> new NoSuchElementException("Album no encontrado"));
    }

    @Override
    public AlbumDto add(AlbumDto model) {
        AlbumEntity albumEntity = this.albumMapper.toEntity(model);
        this.artistService.getById(model.getArtistId());
        AlbumEntity albumEntitySaved = this.albumRepository.save(albumEntity);

        return this.albumMapper.toDto(albumEntitySaved);
    }

    @Override
    public void update(Long id, AlbumDto model) {
        AlbumEntity albumEntity = this.albumRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Album no encontrado"));
        this.artistService.getById(model.getArtistId());
        albumEntity.setTitle(model.getTitle());
        albumEntity.setArtistEntity(
                ArtistEntity
                        .builder().id(model.getArtistId())
                        .build()
        );

        this.albumRepository.save(albumEntity);
    }

    @Override
    public void delete(Long id) {
        AlbumEntity album = this.albumRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Album no encontrado"));
        this.albumRepository.delete(album);

    }
}
