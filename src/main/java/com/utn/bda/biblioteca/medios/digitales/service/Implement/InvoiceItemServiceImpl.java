package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.InvoiceItemDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.InvoiceEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.InvoiceItemEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.TrackEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.InvoiceItemRepository;
import com.utn.bda.biblioteca.medios.digitales.service.InvoiceItemService;
import com.utn.bda.biblioteca.medios.digitales.service.InvoiceService;
import com.utn.bda.biblioteca.medios.digitales.service.TrackService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.InvoiceItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {
    private final InvoiceItemRepository invoiceItemRepository;

    private final InvoiceItemMapper invoiceItemMapper;
    private final TrackService trackService;

    private final InvoiceService invoiceService;
    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository, InvoiceItemMapper invoiceItemMapper, TrackService trackService, InvoiceService invoiceService) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceItemMapper = invoiceItemMapper;
        this.trackService = trackService;
        this.invoiceService = invoiceService;
    }

    @Override
    public List<InvoiceItemDto> getAll() {
        List<InvoiceItemEntity> invoiceItemEntities = this.invoiceItemRepository.findAll();
        return invoiceItemEntities.stream()
                .map(invoiceItemMapper::toDto)
                .toList();
    }

    @Override
    public InvoiceItemDto getById(Long id) {
        InvoiceItemEntity invoiceItemEntity = this.invoiceItemRepository.findById(id).orElseThrow(()-> new NoSuchElementException("InvoiceItem no encontrado"));
        return this.invoiceItemMapper.toDto(invoiceItemEntity);
    }

    @Override
    public InvoiceItemDto add(InvoiceItemDto model) {
        InvoiceItemEntity invoiceItemEntity = this.invoiceItemMapper.toEntity(model);
        this.trackService.getById(model.getTrackId());
        this.invoiceService.getById(model.getInvoiceId());
        InvoiceItemEntity savedInvoiceItemEntity = this.invoiceItemRepository.save(invoiceItemEntity);
        return this.invoiceItemMapper.toDto(savedInvoiceItemEntity);
    }

    @Override
    public void update(Long id, InvoiceItemDto model) {
        InvoiceItemEntity invoiceItemEntity = this.invoiceItemRepository.findById(id).orElseThrow(()-> new NoSuchElementException("InvoiceItem no encontrado"));
        this.trackService.getById(model.getTrackId());
        this.invoiceService.getById(model.getInvoiceId());
        invoiceItemEntity.setUnitPrice(model.getUnitPrice());
        invoiceItemEntity.setQuantity(model.getQuantity());
        invoiceItemEntity.setInvoiceEntity(InvoiceEntity.builder().id(model.getInvoiceId()).build());
        invoiceItemEntity.setTrackEntity(TrackEntity.builder().id(model.getTrackId()).build());
        this.invoiceItemRepository.save(invoiceItemEntity);

    }

    @Override
    public void delete(Long id) {
        InvoiceItemEntity invoiceItemEntity = this.invoiceItemRepository.findById(id).orElseThrow(()-> new NoSuchElementException("InvoiceItem no encontrado"));
        this.invoiceItemRepository.delete(invoiceItemEntity);

    }
}
