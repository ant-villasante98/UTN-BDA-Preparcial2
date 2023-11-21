package com.utn.bda.biblioteca.medios.digitales.service.mapper;

import com.utn.bda.biblioteca.medios.digitales.model.dto.InvoiceDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.CustomerEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.InvoiceEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class InvoiceMapper implements Mapper<InvoiceDto, InvoiceEntity>{
    private final InvoiceItemMapper invoiceItemMapper;

    public InvoiceMapper(InvoiceItemMapper invoiceItemMapper) {
        this.invoiceItemMapper = invoiceItemMapper;
    }

    @Override
    public InvoiceDto toDto(InvoiceEntity model) {
        return new InvoiceDto(
                model.getId(),
                model.getInvoiceDate(),
                model.getBillingAddress(),
                model.getBillingCity(),
                model.getBillingState(),
                model.getBillingCountry(),
                model.getBillingPostalCode(),
                model.getTotal(),
                model.getCustomerEntity().getId(),
                model.getInvoiceItemEntities().stream().map(invoiceItemMapper::toDto).toList()
        );
    }

    @Override
    public InvoiceEntity toEntity(InvoiceDto model) {
        return new InvoiceEntity(
                model.getId(),
                model.getInvoiceDate(),
                model.getBillingAddress(),
                model.getBillingCity(),
                model.getBillingState(),
                model.getBillingCountry(),
                model.getBillingPostalCode(),
                model.getTotal(),
                CustomerEntity.builder().id(model.getCustomer()).build(),
                model.getInvoiceItems().stream().map(invoiceItemMapper::toEntity).toList()
        );
    }
}
