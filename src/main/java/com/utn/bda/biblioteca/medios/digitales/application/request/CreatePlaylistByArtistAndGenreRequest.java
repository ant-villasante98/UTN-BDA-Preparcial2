package com.utn.bda.biblioteca.medios.digitales.application.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaylistByArtistAndGenreRequest {
    private String playlistName;

    private Long artistId;

    private Long genreId;

    private long minutes;

}
