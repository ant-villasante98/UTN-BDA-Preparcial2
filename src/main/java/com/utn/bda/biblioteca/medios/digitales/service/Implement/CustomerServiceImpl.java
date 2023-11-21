package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.CustomerDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.CustomerEntity;
import com.utn.bda.biblioteca.medios.digitales.model.entity.EmployeeEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.CustomerRepository;
import com.utn.bda.biblioteca.medios.digitales.service.CustomerService;
import com.utn.bda.biblioteca.medios.digitales.service.EmployeeService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final EmployeeService employeeService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, EmployeeService employeeService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerEntity> customerEntities = this.customerRepository.findAll();
        return customerEntities.stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<CustomerEntity> optionalCustomer = this.customerRepository.findById(id);
        return optionalCustomer
                .map(customerMapper::toDto).orElseThrow(()-> new NoSuchElementException("Customer no encontrado"));
    }

    @Override
    public CustomerDto add(CustomerDto model) {
        CustomerEntity customerEntity = this.customerMapper.toEntity(model);
        this.employeeService.getById(model.getSuporterEmployee());
        CustomerEntity savedCustomer = this.customerRepository.save(customerEntity);
        return this.customerMapper.toDto(savedCustomer);

    }

    @Override
    public void update(Long id, CustomerDto model) {
        CustomerEntity customerEntity = this.customerMapper.toEntity(model);
        this.employeeService.getById(model.getSuporterEmployee());
        customerEntity.setLastName(model.getLastName());
        customerEntity.setFirstName(model.getFirstName());
        customerEntity.setCompany(model.getCompany());
        customerEntity.setAddress(model.getAddress());
        customerEntity.setCity(model.getCity());
        customerEntity.setState(model.getState());
        customerEntity.setCountry(model.getCountry());
        customerEntity.setPostalCode(model.getPostalCode());
        customerEntity.setPhone(model.getPhone());
        customerEntity.setFax(model.getFax());
        customerEntity.setEmail(model.getEmail());
        customerEntity.setEmployeeEntity(EmployeeEntity.builder().id(model.getSuporterEmployee()).build());
        this.customerRepository.save(customerEntity);

    }

    @Override
    public void delete(Long id) {
        CustomerEntity customer = this.customerRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Customer no encontrado"));
        this.customerRepository.delete(customer);

    }
}
