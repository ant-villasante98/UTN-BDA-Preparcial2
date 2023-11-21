package com.utn.bda.biblioteca.medios.digitales.service.mapper;

import com.utn.bda.biblioteca.medios.digitales.model.dto.CustomerDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.CustomerEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerMapper implements Mapper<CustomerDto, CustomerEntity>{
    @Override
    public CustomerDto toDto(CustomerEntity model) {
        return new CustomerDto(
                model.getId(),
                model.getLastName(),
                model.getFirstName(),
                model.getCompany(),
                model.getAddress(),
                model.getCity(),
                model.getState(),
                model.getCountry(),
                model.getPostalCode(),
                model.getPhone(),
                model.getFax(),
                model.getEmail(),
                model.getEmployeeEntity().getId()
        );
    }

    @Override
    public CustomerEntity toEntity(CustomerDto model) {
        return new CustomerEntity(
                model.getId(),
                model.getLastName(),
                model.getFirstName(),
                model.getCompany(),
                model.getAddress(),
                model.getCity(),
                model.getState(),
                model.getCountry(),
                model.getPostalCode(),
                model.getPhone(),
                model.getFax(),
                model.getEmail(),
                EmployeeEntity.builder().id(model.getSuporterEmployeeId()).build(),
                Collections.emptyList()

        );
    }
}
