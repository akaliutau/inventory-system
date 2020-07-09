package org.warehouse.util;

import lombok.extern.slf4j.Slf4j;
import org.warehouse.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Utility class
 * Used mostly to generate records for demonstration and testing purposes
 *
 * @author Aliaksei Kaliutau
 */
@Slf4j
public class DataUtils {

    public static List<Item> genItems(int amount) {
        long id = 0L;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Item item = new Item(id++, UUID.randomUUID().toString(), String.format("inventory item #%d", i), (long) (1000 * Math.random()));
            items.add(item);
        }
        log.info("generated {} items", items.size());

        return items;
    }

}
