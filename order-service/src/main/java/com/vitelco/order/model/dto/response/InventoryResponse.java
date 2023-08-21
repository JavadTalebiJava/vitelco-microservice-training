package com.vitelco.order.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    private Long id;

    private String sku;
    private String name;
    private float qty;

    private boolean inStock;
}
