package com.utn.bda.biblioteca.medios.digitales.model.dto;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateAlbumRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumDto {
    private long id;

    private String title;

    private Long artistId;

    public static AlbumDto from(CreateAlbumRequest albumRequest){
        return new AlbumDto(
                0,
                albumRequest.getTitle(),
                albumRequest.getArtistId()
        );
    }
}
