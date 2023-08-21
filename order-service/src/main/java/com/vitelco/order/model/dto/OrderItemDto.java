package com.vitelco.order.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private Long id;

    private LocalDateTime createdDated;

    private String name;
    private String sku;
    private float qty;
    private BigDecimal price;

    //@JsonIgnore
    //private Order order;
}
