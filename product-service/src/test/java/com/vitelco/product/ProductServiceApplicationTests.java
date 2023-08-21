package com.vitelco.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitelco.product.model.dto.request.ProductRequest;
import com.vitelco.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
class ProductServiceApplicationTests {

	@Container
	private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = ProductRequest.builder()
				.name("Magic Mouse2")
				.price(BigDecimal.valueOf(3100))
				.build();

		String productJson = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productJson))
				.andExpect(status().isCreated());



		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	@DynamicPropertySource
	static void setDynamicProps(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.url", mongoDBContainer::getReplicaSetUrl);
		dynamicPropertyRegistry.add("spring.data.mongodb.database", TestConfig::dbName);
	}

}
