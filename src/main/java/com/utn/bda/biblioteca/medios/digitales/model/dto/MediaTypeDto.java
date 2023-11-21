package com.utn.bda.biblioteca.medios.digitales.model.dto;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateMediaTypeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaTypeDto {
    private Long id;

    private String name;

    public static MediaTypeDto from(CreateMediaTypeRequest mediaTypeRequest){
        return new MediaTypeDto(
                0L,
                mediaTypeRequest.getName()
        );
    }
}
