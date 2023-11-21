package com.utn.bda.biblioteca.medios.digitales.model.dto;


import com.utn.bda.biblioteca.medios.digitales.application.request.CreateInvoiceItemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItemDto {
    private Long id;

    private Float unitPrice;

    private Long quantity;
    private Long invoiceId;
    private Long trackId;

    public static InvoiceItemDto from(CreateInvoiceItemRequest invoiceItemRequest){
        return new InvoiceItemDto(
                0L,
                invoiceItemRequest.getUnitPrice(),
                invoiceItemRequest.getQuantity(),
                invoiceItemRequest.getInvoiceId(),
                invoiceItemRequest.getTrackId()
        );
    }
}
