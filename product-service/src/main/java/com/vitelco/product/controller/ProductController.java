package com.vitelco.product.controller;

import com.vitelco.product.model.dto.request.ProductRequest;
import com.vitelco.product.model.dto.response.ProductResponse;
import com.vitelco.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ProductRequest productRequest){
        productService.save(productRequest.convert());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAll(){
        return productService.findAll();
    }
}
