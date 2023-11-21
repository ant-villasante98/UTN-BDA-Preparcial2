package com.utn.bda.biblioteca.medios.digitales.application.controller;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateInvoiceRequest;
import com.utn.bda.biblioteca.medios.digitales.model.dto.InvoiceDto;
import com.utn.bda.biblioteca.medios.digitales.service.InvoiceService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<InvoiceDto> invoiceDtos = this.invoiceService.getAll();
        return new ResponseEntity<>(invoiceDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        try{
            InvoiceDto invoiceDto = this.invoiceService.getById(id);
            return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateInvoiceRequest invoiceRequest){
        try{
            InvoiceDto invoiceDto = this.invoiceService.add(InvoiceDto.from(invoiceRequest));
            return new ResponseEntity<>(invoiceDto, HttpStatus.CREATED);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id ,@RequestBody InvoiceDto invoiceDto){
        try{
            this.invoiceService.update(id, invoiceDto);
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
            this.invoiceService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
