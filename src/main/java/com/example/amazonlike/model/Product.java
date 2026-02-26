package com.example.amazonlike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    @Min(value = 0, message = "Stock must be non-negative")
    private Integer stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order> orders;
}
