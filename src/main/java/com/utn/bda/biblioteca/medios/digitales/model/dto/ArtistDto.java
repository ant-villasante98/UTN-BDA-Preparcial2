package com.utn.bda.biblioteca.medios.digitales.model.dto;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateArtistRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistDto {
    private long id;

    private String name;

    public static ArtistDto from(CreateArtistRequest artistRequest){
        return new ArtistDto(
                0,
                artistRequest.getName()
        );
    }
}
