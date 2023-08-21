package com.vitelco.product.model.dto.request;

import com.vitelco.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String name;
    private BigDecimal price;

    public Product convert(){
       return Product.builder()
               .name(this.getName())
               .price(this.getPrice())
               .build();

    }
}
