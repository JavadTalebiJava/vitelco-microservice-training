package com.vitelco.product.service;

import com.vitelco.product.model.Product;
import com.vitelco.product.model.dto.response.ProductResponse;
import com.vitelco.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    /**
     * Fetch all products from db
     *
     * @return
     */
    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * converts Product to ProductResponse
     * @param product
     * @return
     */
    private ProductResponse mapToResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
    
    /**
     * Save product to DB
     *
     * @param product
     * @return
     */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
