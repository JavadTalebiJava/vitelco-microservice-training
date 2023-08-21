package com.vitelco.inventory.controller;

import com.vitelco.inventory.model.dto.request.InventoryRequest;
import com.vitelco.inventory.model.dto.response.InventoryResponse;
import com.vitelco.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.getStockInventoryStatus(inventoryRequest.getSkuCodes());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> fetchAll() {
        return inventoryService.findAll();
    }
}
