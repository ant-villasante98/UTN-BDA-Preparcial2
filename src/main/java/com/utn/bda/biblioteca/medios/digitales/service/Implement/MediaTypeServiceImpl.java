package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.MediaTypeDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.MediaTypeEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.MediaTypeRepository;
import com.utn.bda.biblioteca.medios.digitales.service.MediaTypeService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.MediaTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MediaTypeServiceImpl implements MediaTypeService{

    private final MediaTypeRepository mediaTypeRepository;
    private final MediaTypeMapper mediaTypeMapper;

    public MediaTypeServiceImpl(MediaTypeRepository mediaTypeRepository, MediaTypeMapper mediaTypeMapper) {
        this.mediaTypeRepository = mediaTypeRepository;
        this.mediaTypeMapper = mediaTypeMapper;
    }

    @Override
    public List<MediaTypeDto> getAll() {
        List<MediaTypeEntity> mediaTypeEntities = this.mediaTypeRepository.findAll();
        return mediaTypeEntities.stream()
                .map(mediaTypeMapper::toDto)
                .toList();
    }

    @Override
    public MediaTypeDto getById(Long id) {
        MediaTypeEntity mediaTypeEntity = this.mediaTypeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("MediaType no encontrado"));
        return this.mediaTypeMapper.toDto(mediaTypeEntity);
    }

    @Override
    public MediaTypeDto add(MediaTypeDto model) {
        MediaTypeEntity mediaTypeEntity = this.mediaTypeMapper.toEntity(model);
        MediaTypeEntity savedMediaType = this.mediaTypeRepository.save(mediaTypeEntity);
        return this.mediaTypeMapper.toDto(savedMediaType);
    }

    @Override
    public void update(Long id, MediaTypeDto model) {
        MediaTypeEntity mediaTypeEntity = this.mediaTypeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("MediaType no encontrado"));
        mediaTypeEntity.setName(model.getName());
        this.mediaTypeRepository.save(mediaTypeEntity);
    }

    @Override
    public void delete(Long id) {
        MediaTypeEntity mediaTypeEntity = this.mediaTypeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("MediaType no encontrado"));
        this.mediaTypeRepository.delete(mediaTypeEntity);
    }
}
