package com.utn.bda.biblioteca.medios.digitales.application.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
    private LocalDateTime invoiceDate;

    private String billingAddress;

    private String billingCity;

    private String billingState;

    private String billingCountry;

    private String billingPostalCode;

    private Long total;

    private Long customerId;

    private List<CreateInvoiceItemRequest> invoiceItems;
}
