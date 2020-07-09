package org.warehouse.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.warehouse.model.Item;
import org.warehouse.service.InventoryService;

@RestController
@RequestMapping("/api/v1/items")
@Slf4j
@Api(value = "Inventory System")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @ApiOperation(value = "Get all records from inventory", response = Page.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved records"),
            @ApiResponse(code = 400, message = "Wrong input"),
            @ApiResponse(code = 404, message = "The resource is not found")})
    @GetMapping
    public ResponseEntity<Page<Item>> getAll(
            @ApiParam(value = "Page to be requested", required = false)
            @RequestParam(required = false, defaultValue = "0") int page) {
        log.info("requesting page {}", page);
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Page<Item> res = inventoryService.getAll(page);
        if (res.getTotalPages() < page) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(res);
    }

}
