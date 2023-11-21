package com.utn.bda.biblioteca.medios.digitales.service.mapper;

import com.utn.bda.biblioteca.medios.digitales.model.dto.InvoiceItemDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.InvoiceEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.InvoiceItemEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import org.springframework.stereotype.Service;

@Service
public class InvoiceItemMapper implements Mapper<InvoiceItemDto, InvoiceItemEntity> {
    @Override
    public InvoiceItemDto toDto(InvoiceItemEntity model) {
        return new InvoiceItemDto(
                model.getId(),
                model.getUnitPrice(),
                model.getQuantity(),
                model.getInvoiceEntity().getId(),
                model.getTrackEntity().getId()
        );
    }

    @Override
    public InvoiceItemEntity toEntity(InvoiceItemDto model) {
        return new InvoiceItemEntity(
                model.getId(),
                model.getUnitPrice(),
                model.getQuantity(),
                InvoiceEntity.builder().id(model.getInvoiceId()).build(),
                TrackEntity.builder().id(model.getTrackId()).build()
        );
    }
}
