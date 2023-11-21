package com.utn.bda.biblioteca.medios.digitales.service.mapper;

import com.utn.bda.biblioteca.medios.digitales.model.dto.GenreDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.GenreEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class GenreMapper implements Mapper<GenreDto, GenreEntity>{
    @Override
    public GenreDto toDto(GenreEntity model) {
        return new GenreDto(
                model.getId(),
                model.getName()
        );
    }

    @Override
    public GenreEntity toEntity(GenreDto model) {
        return new GenreEntity(
                model.getId(),
                model.getName(),
                Collections.emptyList()
        );
    }
}
