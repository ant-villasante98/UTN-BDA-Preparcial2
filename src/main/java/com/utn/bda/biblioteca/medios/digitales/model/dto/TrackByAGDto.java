package com.utn.bda.biblioteca.medios.digitales.model.dto;

import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackByAGDto {
    private long trackId;
    private String trackName;
    private String albumTitle;
    private String artistName;
    private long milliseconds;

    public static TrackByAGDto from(TrackEntity trackEntity){
        return new TrackByAGDto(
                trackEntity.getId(),
                trackEntity.getName(),
                trackEntity.getAlbumEntity().getTitle(),
                trackEntity.getAlbumEntity().getArtistEntity().getName(),
                trackEntity.getMilliseconds()
        );
    }
}
