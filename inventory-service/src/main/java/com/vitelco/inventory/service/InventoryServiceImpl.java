package com.vitelco.inventory.service;

import com.vitelco.inventory.model.Inventory;
import com.vitelco.inventory.model.dto.request.InventoryRequest;
import com.vitelco.inventory.model.dto.response.InventoryResponse;
import com.vitelco.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * create inventory record
     *
     * @param inventoryRequest
     */
    @Override
    public void save(InventoryRequest inventoryRequest) {

    }

    /**
     * Fetches all Inventories from the database.
     *
     * @return A list containing all the Inventories available in the database.
     */
    @Override
    public List<InventoryResponse> findAll() {
        return inventoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * checks the inventory for the stock availability of a stock by barcode
     *
     * @param skuCodes
     * @return A list of InventoryResponse objects representing the availability of the product.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> getStockInventoryStatus(List<String> skuCodes) {
        List<Inventory> lst = inventoryRepository.findInventoriesBySkuIn(skuCodes);

        return lst.stream()
                .map(inventory -> InventoryResponse.builder()
                        .id(inventory.getId())
                        .sku(inventory.getSku())
                        .name(inventory.getName())
                        .qty(inventory.getQty())
                        .inStock(inventory.getQty() > 0)
                        .build())
                .toList();

    }

    /**
     * Maps a {@link Inventory} object to a {@link InventoryResponse} object.
     *
     * @param inventory The {@link Inventory} object to be converted into the response format.
     * @return A {@link InventoryResponse} object representing the mapped inventory.
     */
    private InventoryResponse mapToResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .sku(inventory.getSku())
                .name(inventory.getName())
                .qty(inventory.getQty())
                .build();
    }


}
