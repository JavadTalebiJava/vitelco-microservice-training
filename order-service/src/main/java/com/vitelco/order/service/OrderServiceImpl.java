package com.vitelco.order.service;

import com.vitelco.order.model.Order;
import com.vitelco.order.model.OrderItem;
import com.vitelco.order.model.dto.OrderItemDto;
import com.vitelco.order.model.dto.request.InventoryRequest;
import com.vitelco.order.model.dto.request.OrderRequest;
import com.vitelco.order.model.dto.response.InventoryResponse;
import com.vitelco.order.model.dto.response.OrderResponse;
import com.vitelco.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    /**
     * PlaceOrder
     *
     * @param orderRequest
     */
    @Override
    public void placeOrder(OrderRequest orderRequest) throws URISyntaxException {
        List<String> skuCodes = orderRequest.getOrderItems()
                .stream()
                .map(OrderItemDto::getSku)
                .toList();

        //check inventory for each product
        boolean allProductInStock = checkStockInventoryStatusAvailability(skuCodes);

        if (!allProductInStock)
            throw new IllegalArgumentException("Product is not in stock!");

        orderRepository.save(orderRequest.convert());
    }

    private boolean checkStockInventoryStatusAvailability(List<String> skuCodes) throws URISyntaxException {
        URI inventoryUri = new URI("http://localhost:4002/v1/inventory/check");
        HttpHeaders header =new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        InventoryRequest inventoryRequest = InventoryRequest.builder().skuCodes(skuCodes).build();

        HttpEntity<InventoryRequest> httpEntity = new HttpEntity<>(inventoryRequest,header);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<InventoryResponse>> responseEntity =  restTemplate.exchange(
                inventoryUri,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>(){});

        List<InventoryResponse> lst = responseEntity.getBody();
        if (lst != null && lst.size()>0){
            return lst.stream().allMatch(InventoryResponse::isInStock);
        }
        return false;
    }

    /**
     * Fetches all orders from the database.
     *
     * @return A list containing all the orders available in the database.
     */
    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList()
                ;
    }

    private OrderResponse mapToResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .orderItems(
                        order.getOrderItems().stream().map(this::mapToOrderItemDto).collect(Collectors.toList())
                )
                .customerName(order.getCustomerName())
                .createdDated(order.getCreatedDated())
                .build();
    }

    private OrderItemDto mapToOrderItemDto(OrderItem orderItem){
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .sku(orderItem.getSku())
                .qty(orderItem.getQty())
                .name(orderItem.getName())
                .createdDated(orderItem.getCreatedDated())
                .price(orderItem.getPrice())
                .build();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }


}
