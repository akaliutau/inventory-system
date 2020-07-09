package org.warehouse;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.warehouse.model.Item;
import org.warehouse.repository.ItemRepository;
import org.warehouse.util.DataUtils;

import java.util.List;


@Slf4j
@SpringBootApplication
@ComponentScan("org.warehouse")
public class MainApp {

    @Autowired
    ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            log.info("app started");
            List<Item> items = DataUtils.genItems(10);
            List<Item> persisted = itemRepository.saveAll(items);
            log.info("total items saved: " + persisted.size());

        };
    }

}
