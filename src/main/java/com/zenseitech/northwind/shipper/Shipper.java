package com.zenseitech.northwind.shipper;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Shipper")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Shipper {

    public Shipper() {

    }

    @Id
    private Integer id;

    private String companyName;
    private String phone;

    /**
     * w2ui unique required value
     * @return
     */
    public Integer getRecId() {
        return id;
    }
}
