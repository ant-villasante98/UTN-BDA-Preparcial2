package com.utn.bda.biblioteca.medios.digitales.application.controller;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateInvoiceItemRequest;
import com.utn.bda.biblioteca.medios.digitales.model.dto.InvoiceItemDto;
import com.utn.bda.biblioteca.medios.digitales.service.InvoiceItemService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.InvoiceItemMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/invoiceitems")
public class InvoiceItemController {
    private final InvoiceItemService invoiceItemService;

    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try {
        List<InvoiceItemDto> invoiceItemDtos = this.invoiceItemService.getAll();
        return new ResponseEntity<>(invoiceItemDtos, HttpStatus.OK);
        }catch (Exception ex){
        ex.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        try{
            InvoiceItemDto invoiceItemDto = this.invoiceItemService.getById(id);
            return new ResponseEntity<>(invoiceItemDto , HttpStatus.OK);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateInvoiceItemRequest invoiceItemRequest){
        try{
            InvoiceItemDto invoiceItemDto = this.invoiceItemService.add(InvoiceItemDto.from(invoiceItemRequest));
            return new ResponseEntity<>(invoiceItemDto, HttpStatus.CREATED);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id ,@RequestBody InvoiceItemDto invoiceItemDto){
        try{
            this.invoiceItemService.update(id, invoiceItemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id){
        try{
            this.invoiceItemService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
