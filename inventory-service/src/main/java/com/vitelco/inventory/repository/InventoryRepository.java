package com.vitelco.inventory.repository;

import com.vitelco.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findInventoriesBySkuIn(List<String> skuCodes);


}
