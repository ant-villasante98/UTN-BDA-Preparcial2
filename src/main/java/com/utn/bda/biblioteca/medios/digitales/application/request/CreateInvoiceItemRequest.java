package com.utn.bda.biblioteca.medios.digitales.application.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceItemRequest {

    private Float unitPrice;

    private Long quantity;
    private Long invoiceId;
    private Long trackId;
}
