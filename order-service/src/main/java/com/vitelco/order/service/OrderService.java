package com.vitelco.order.service;

import com.vitelco.order.model.Order;
import com.vitelco.order.model.dto.request.OrderRequest;
import com.vitelco.order.model.dto.response.OrderResponse;

import java.net.URISyntaxException;
import java.util.List;

public interface OrderService {

    /**
     * Fetches all orders from the database.
     *
     * @return A list containing all the orders available in the database.
     */
    List<OrderResponse> findAll();

    Order save(Order order);

    /**
     * PlaceOrder
     *
     * @param orderRequest
     */
    void placeOrder(OrderRequest orderRequest) throws URISyntaxException;
}
