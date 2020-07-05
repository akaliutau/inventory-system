package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.warehouse.model.Item;
import org.warehouse.service.InventoryService;

import io.swagger.annotations.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/items")
@Slf4j
@Api(value = "Inventory System")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @ApiOperation(value = "Get all records from inventory", response = Page.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved records")})
    @GetMapping
    public ResponseEntity<Page<Item>> getAll(
            @ApiParam(value = "Page to be requested", required = false)
            @RequestParam(required = false, defaultValue = "0") int page) {
        log.info("requesting page {}", page);
        Page<Item> res = inventoryService.getAll(page);
        return ResponseEntity.ok().body(res);
    }

}
