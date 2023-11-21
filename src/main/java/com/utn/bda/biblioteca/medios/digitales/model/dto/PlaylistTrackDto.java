package com.utn.bda.biblioteca.medios.digitales.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTrackDto {
    private Long playlistId;
    private Long trackId;
}
