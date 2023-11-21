package com.utn.bda.biblioteca.medios.digitales.service.mapper;

import com.utn.bda.biblioteca.medios.digitales.model.dto.MediaTypeDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.MediaTypeEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MediaTypeMapper implements Mapper<MediaTypeDto, MediaTypeEntity>{
    @Override
    public MediaTypeDto toDto(MediaTypeEntity model) {
        return new MediaTypeDto(
                model.getId(),
                model.getName()
        );
    }

    @Override
    public MediaTypeEntity toEntity(MediaTypeDto model) {
        return new MediaTypeEntity(
                model.getId(),
                model.getName(),
                Collections.emptyList()
        );
    }
}
