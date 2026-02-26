package com.example.amazonlike.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private Double totalPrice;
    private LocalDateTime orderDate;
}
