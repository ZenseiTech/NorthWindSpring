package com.zenseitech.northwind.category;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {

    public Category() {

    }

    @Id
    private int Id;
    private String categoryName;
    private String description;
}

