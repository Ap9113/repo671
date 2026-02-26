package com.example.amazonlike.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    @Min(value = 0, message = "Stock must be non-negative")
    private Integer stock;
}
