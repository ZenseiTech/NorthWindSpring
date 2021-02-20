package com.zenseitech.northwind.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerChanges {
    private String recid;
    private String companyName;
    private String contactName;
    private String address;
    private String city;
    private String contactTitle;
    private String country;
    private String fax;
    private String phone;
    private String postalCode;
    private String region;

    public void update(Customer customer) {
        if (getCompanyName() != null) {
            customer.setCompanyName(getCompanyName());
        }
        if (getContactName() != null) {
            customer.setContactName(getContactName());
        }
        if (getAddress() != null) {
            customer.setAddress(getAddress());
        }
        if (getCity() != null) {
            customer.setCity(getCity());
        }
        if (getContactTitle() != null) {
            customer.setContactTitle(getContactTitle());
        }
        if (getCountry() != null) {
            customer.setCountry(getCountry());
        }
        if (getFax() != null) {
            customer.setFax(getFax());
        }
        if (getPhone() != null) {
            customer.setPhone(getPhone());
        }
        if (getPostalCode() != null) {
            customer.setPostalCode(getPostalCode());
        }
        if (getRegion() != null) {
            customer.setRegion(getRegion());
        }
    }
}
