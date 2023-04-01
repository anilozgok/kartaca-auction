package com.anilcan.kartaca.config;

import com.anilcan.kartaca.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitializeItemsConfig {

    private final ItemService itemService;

    @Bean
    public void initializeItems() {
        itemService.initializeItems();
    }

}
