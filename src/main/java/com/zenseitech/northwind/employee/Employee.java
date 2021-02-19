package com.zenseitech.northwind.employee;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Employee")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

    public Employee() {

    }

    @Id
    private Integer id;

    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private String birthDate;
    private String hireDate;
    private String address;

    public String getFullName() {
        return this.firstName + " " + lastName;
    }

    public void setFromFullName(String fullName) {
        String [] names = fullName.split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
    }

    /**
     * w2ui unique required value
     * @return
     */
    public Integer getRecid() {
        return id;
    }
}
