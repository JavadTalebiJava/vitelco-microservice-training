package com.vitelco.order.controller;


import com.vitelco.order.model.dto.request.OrderRequest;
import com.vitelco.order.model.dto.response.OrderResponse;
import com.vitelco.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody OrderRequest orderRequest) throws URISyntaxException {
        orderService.placeOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAll(){
        return orderService.findAll();
    }
}
