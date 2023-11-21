package com.utn.bda.biblioteca.medios.digitales.service.Implement;

import com.utn.bda.biblioteca.medios.digitales.model.dto.EmployeeDto;
import com.utn.bda.biblioteca.medios.digitales.model.entity.EmployeeEntity;
import com.utn.bda.biblioteca.medios.digitales.repository.EmployeeRepository;
import com.utn.bda.biblioteca.medios.digitales.service.EmployeeService;
import com.utn.bda.biblioteca.medios.digitales.service.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employeeEntities = this.employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto getById(Long id) {
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(id);

        return employeeEntity.map(employeeMapper::toDto).orElseThrow(()-> new NoSuchElementException("Employee no encontrado con el "+id));
    }

    @Override
    public EmployeeDto add(EmployeeDto model) {
         EmployeeEntity employeeEntity = this.employeeMapper.toEntity(model);
         if(model.getReportsTo() != null){
             this.getById(model.getReportsTo());
         }
        EmployeeEntity employeeEntitySaved = this.employeeRepository.save(employeeEntity);

        return this.employeeMapper.toDto(employeeEntitySaved);
    }

    @Override
    public void update(Long id, EmployeeDto model) {
        EmployeeEntity employeeEntity = this.employeeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Employee no encontrado con el id "+ id));
        if (model.getReportsTo() != null) {
            this.getById(model.getReportsTo());
        }

        employeeEntity.setLastName(model.getLastName());
        employeeEntity.setFirstName(model.getFirstName());
        employeeEntity.setTitle(model.getTitle());
        employeeEntity.setBirthDate(model.getBirthDate());
        employeeEntity.setHireDate(model.getHireDate());
        employeeEntity.setAddress(model.getAddress());
        employeeEntity.setCity(model.getCity());
        employeeEntity.setState(model.getState());
        employeeEntity.setCountry(model.getCountry());
        employeeEntity.setPostalCode(model.getPostalCode());
        employeeEntity.setPhone(model.getPhone());
        employeeEntity.setFax(model.getFax());
        employeeEntity.setEmail(model.getEmail());
        employeeEntity.setReportsTo(EmployeeEntity.builder().id(model.getReportsTo()).build());

        this.employeeRepository.save(employeeEntity);
    }

    @Override
    public void delete(Long id) {
        EmployeeEntity employeeEntity = this.employeeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Employee no encontrado"));
        this.employeeRepository.save(employeeEntity);
    }
}
