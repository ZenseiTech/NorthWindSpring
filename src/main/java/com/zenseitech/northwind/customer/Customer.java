package com.zenseitech.northwind.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String CompanyName;
    private String ContactName;
    private String ContactTitle;
    private String Address;
    private String City;
    private String Region;
    private String PostalCode;
    private String Country;
    private String Phone;
    private String Fax;

    public Customer(String id,
                    String companyName,
                    String contactName,
                    String contactTitle,
                    String address,
                    String city,
                    String region,
                    String postalCode,
                    String country,
                    String phone,
                    String fax) {
        this.id = id;
        CompanyName = companyName;
        ContactName = contactName;
        ContactTitle = contactTitle;
        Address = address;
        City = city;
        Region = region;
        PostalCode = postalCode;
        Country = country;
        Phone = phone;
        Fax = fax;
    }
}
