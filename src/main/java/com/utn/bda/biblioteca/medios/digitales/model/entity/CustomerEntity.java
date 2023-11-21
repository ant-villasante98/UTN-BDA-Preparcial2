package com.utn.bda.biblioteca.medios.digitales.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    private long id;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    private String company;

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
    @JoinColumn(name = "supportrepid", referencedColumnName = "employeeid")
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "customerEntity")
    private List<InvoiceEntity> invoiceEntities;

}
