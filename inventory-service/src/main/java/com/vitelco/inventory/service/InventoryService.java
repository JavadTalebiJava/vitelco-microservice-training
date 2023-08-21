package com.vitelco.inventory.service;


import com.vitelco.inventory.model.dto.request.InventoryRequest;
import com.vitelco.inventory.model.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryService {

    /**
     * create inventory record
     *
     * @param inventoryRequest
     */
    void save(InventoryRequest inventoryRequest);

    /**
     * Fetches all Inventories from the database.
     *
     * @return A list containing all the Inventories available in the database.
     */
    List<InventoryResponse> findAll();

    /**
     * checks the inventory for the stock availability of a stock by barcode
     *
     * @param skuCodes
     * @return A list of InventoryResponse objects representing the availability of the product.
     */
    List<InventoryResponse> getStockInventoryStatus(List<String> skuCodes);
}
