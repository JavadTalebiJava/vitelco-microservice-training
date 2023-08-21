package com.vitelco.product.service;

import com.vitelco.product.model.Product;
import com.vitelco.product.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    /**
     * Fetch all products from db
     * @return
     */
    List<ProductResponse> findAll();

    /**
     * Save product to DB
     * @param product
     * @return
     */
    Product save(Product product);
}
