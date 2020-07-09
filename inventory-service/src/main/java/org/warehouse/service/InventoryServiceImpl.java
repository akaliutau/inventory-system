package org.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.warehouse.model.Item;
import org.warehouse.repository.ItemRepository;

/**
 * An implementation of {@link InventoryService}
 *
 * @author Aliaksei Kaliutau
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Value(value = "${page.size}")
    private int pageSize;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Page<Item> getAll(int page) {
        if (page < 0){
            throw new IllegalArgumentException("Page must be a non-negative number");
        }
        Pageable p = PageRequest.of(page, pageSize);
        return itemRepository.findAll(p);
    }
}
