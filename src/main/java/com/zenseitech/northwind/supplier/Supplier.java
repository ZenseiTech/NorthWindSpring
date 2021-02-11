package com.zenseitech.northwind.supplier;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {

    public Supplier() {
        
    }

    @Id
    private int id;
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
    private String homePage;
}
