package com.vitelco.inventory;

import com.vitelco.inventory.model.Inventory;
import com.vitelco.inventory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository) {
		return args -> {
			inventoryRepository.saveAll(
					Set.of(Inventory.builder().sku("1111").name("iphone13").qty(100).createdDate(LocalDateTime.now()).build(),
							Inventory.builder().sku("2222").name("iphone14").qty(1).createdDate(LocalDateTime.now()).build()));
		};
	}
}
