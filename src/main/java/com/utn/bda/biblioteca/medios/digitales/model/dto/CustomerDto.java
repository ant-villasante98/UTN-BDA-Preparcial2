package com.utn.bda.biblioteca.medios.digitales.model.dto;


import com.utn.bda.biblioteca.medios.digitales.application.request.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private long id;

    private String lastName;

    private String firstName;

    private String company;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String phone;

    private String fax;

    private String email;

    private Long suporterEmployeeId;

    public static CustomerDto from(CreateCustomerRequest customerRequest){
        return new CustomerDto(
                0,
                customerRequest.getLastName(),
                customerRequest.getFirstName(),
                customerRequest.getCompany(),
                customerRequest.getAddress(),
                customerRequest.getCity(),
                customerRequest.getState(),
                customerRequest.getCountry(),
                customerRequest.getPostalCode(),
                customerRequest.getPhone(),
                customerRequest.getFax(),
                customerRequest.getEmail(),
                customerRequest.getSuporterEmployeeId()
        );
    }

}
