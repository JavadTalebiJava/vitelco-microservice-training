package com.vitelco.order.model.dto.request;

import com.vitelco.order.model.Order;
import com.vitelco.order.model.OrderItem;
import com.vitelco.order.model.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private String customerName;
    private List<OrderItemDto> orderItems;

    public Order convert() {
        return Order.builder()
                .customerName(this.getCustomerName())
                .createdDated(LocalDateTime.now())
                .orderItems(orderItems.stream().map(this::convertOrderItem).toList())
                .build();
    }

    public OrderItem convertOrderItem(OrderItemDto dto){
        return OrderItem.builder()
                .sku(dto.getSku())
                .qty(dto.getQty())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }
}
