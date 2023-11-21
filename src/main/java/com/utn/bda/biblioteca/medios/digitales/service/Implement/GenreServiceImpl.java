package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.GenreDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.GenreEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.GenreRepository;
import com.utn.bda.biblioteca.medios.digitales.service.GenreService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.GenreMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDto> getAll() {
        List<GenreEntity> genreEntities = this.genreRepository.findAll();
        return genreEntities.stream()
                .map(genreMapper::toDto)
                .toList();
    }

    @Override
    public GenreDto getById(Long id) {
        GenreEntity genreEntity = this.genreRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Genre no encontrado"));
        return this.genreMapper.toDto(genreEntity);
    }

    @Override
    public GenreDto add(GenreDto model) {
        GenreEntity genreEntity = this.genreMapper.toEntity(model);
        GenreEntity savedGenre = this.genreRepository.save(genreEntity);
        return this.genreMapper.toDto(savedGenre);
    }

    @Override
    public void update(Long id, GenreDto model) {
        GenreEntity genreEntity = this.genreRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Genre no encontrado"));
        genreEntity.setName(model.getName());
        this.genreRepository.save(genreEntity);
    }

    @Override
    public void delete(Long id) {
        GenreEntity genreEntity = this.genreRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Genre no encontrado"));
        this.genreRepository.delete(genreEntity);
    }
}
