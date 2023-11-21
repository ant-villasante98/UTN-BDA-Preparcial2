package com.utn.bda.biblioteca.medios.digitales.model.dto;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateGenreRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDto {
    private long id;
    private String name;

    public static GenreDto from(CreateGenreRequest genreRequest){
        return new GenreDto(
                0,
                genreRequest.getName()
        );
    }
}
