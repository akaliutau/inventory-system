package org.warehouse.service;

import org.springframework.data.domain.Page;
import org.warehouse.model.Item;

public interface InventoryService {

    Page<Item> getAll(int page);

}