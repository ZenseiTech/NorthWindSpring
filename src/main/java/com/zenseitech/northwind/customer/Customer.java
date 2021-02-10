package com.zenseitech.northwind.customer;

import com.zenseitech.northwind.util.SpecificationRoot;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer  {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
}
