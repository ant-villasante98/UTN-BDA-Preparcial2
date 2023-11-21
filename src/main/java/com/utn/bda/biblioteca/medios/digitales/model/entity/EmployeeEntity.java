package com.utn.bda.biblioteca.medios.digitales.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid")
    private Long id;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    private String title;

    @Column(name = "birthdate")
    private LocalDateTime birthDate;

    @Column(name = "hiredate")
    private LocalDateTime hireDate;

    private String address;

    private String city;

    private String state;

    private String country;

    @Column(name = "postalcode")
    private String postalCode;

    private String phone;

    private String fax;

    private String email;

    @ManyToOne
    @JoinColumn(
            name = "reportsto",
            referencedColumnName = "employeeid"
    )
    private EmployeeEntity reportsTo;

    @OneToMany(mappedBy = "reportsTo")
    private List<EmployeeEntity> employeeEntities;

    @OneToMany(mappedBy = "employeeEntity")
    private List<CustomerEntity> customerEntities;


}
