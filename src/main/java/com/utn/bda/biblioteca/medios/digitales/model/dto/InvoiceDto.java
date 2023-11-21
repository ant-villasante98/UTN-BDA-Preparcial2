package com.utn.bda.biblioteca.medios.digitales.model.dto;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateInvoiceRequest;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDto {
    private Long id;

    private LocalDateTime invoiceDate;

    private String billingAddress;

    private String billingCity;

    private String billingState;

    private String billingCountry;

    private String billingPostalCode;

    private Long total;
    private Long customer;
    private List<InvoiceItemDto> invoiceItems;

    public static InvoiceDto from(CreateInvoiceRequest invoiceRequest){
        return new InvoiceDto(
                0L,
                invoiceRequest.getInvoiceDate(),
                invoiceRequest.getBillingAddress(),
                invoiceRequest.getBillingCity(),
                invoiceRequest.getBillingState(),
                invoiceRequest.getBillingCountry(),
                invoiceRequest.getBillingPostalCode(),
                invoiceRequest.getTotal(),
                invoiceRequest.getCustomer(),
                invoiceRequest.getInvoiceItems().stream().map(InvoiceItemDto::from).toList()
        );
    }
}
