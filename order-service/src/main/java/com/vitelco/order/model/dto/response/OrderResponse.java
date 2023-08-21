package com.vitelco.order.model.dto.response;

import com.vitelco.order.model.OrderItem;
import com.vitelco.order.model.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;

    private String customerName;

    private LocalDateTime createdDated;

    private List<OrderItemDto> orderItems;
}
