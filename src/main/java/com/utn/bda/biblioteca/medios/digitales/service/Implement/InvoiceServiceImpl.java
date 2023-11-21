package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.InvoiceDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.CustomerEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.InvoiceEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.InvoiceRepository;
import com.utn.bda.biblioteca.medios.digitales.service.CustomerService;
import com.utn.bda.biblioteca.medios.digitales.service.InvoiceService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.InvoiceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerService customerService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerService customerService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerService = customerService;
    }

    @Override
    public List<InvoiceDto> getAll() {
        List<InvoiceEntity> invoiceEntities = this.invoiceRepository.findAll();
        return invoiceEntities.stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    @Override
    public InvoiceDto getById(Long id) {
        InvoiceEntity invoiceEntity = this.invoiceRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Invoice no encontrado"));
        return this.invoiceMapper.toDto(invoiceEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public InvoiceDto add(InvoiceDto model) {
        this.customerService.getById(model.getCustomerId());
        InvoiceEntity invoiceEntity = this.invoiceMapper.toEntity(model);
        InvoiceEntity savedInvoice = this.invoiceRepository.save(invoiceEntity);
        return this.invoiceMapper.toDto(savedInvoice);
    }

    @Override
    public void update(Long id, InvoiceDto model) {
        InvoiceEntity invoiceEntity = this.invoiceRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Invoice no encontrado"));
        this.customerService.getById(model.getCustomerId());
        invoiceEntity.setInvoiceDate(model.getInvoiceDate());
        invoiceEntity.setBillingAddress(model.getBillingAddress());
        invoiceEntity.setBillingCity(model.getBillingCity());
        invoiceEntity.setBillingState(model.getBillingState());
        invoiceEntity.setBillingCountry(model.getBillingCountry());
        invoiceEntity.setBillingPostalCode(model.getBillingPostalCode());
        invoiceEntity.setTotal(model.getTotal());
        invoiceEntity.setCustomerEntity(CustomerEntity.builder().id(model.getCustomerId()).build());
        this.invoiceRepository.save(invoiceEntity);

    }

    @Override
    public void delete(Long id) {
        InvoiceEntity invoiceEntity = this.invoiceRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Invoice no encontrado"));
        this.invoiceRepository.delete(invoiceEntity);
    }
}
