package com.anilcan.kartaca.service;

import com.anilcan.kartaca.model.dto.ItemDTO;
import com.anilcan.kartaca.model.entity.Item;
import com.anilcan.kartaca.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void initializeItems() {
        log.info("Items initializing.");

        var item1 = Item.builder().itemName("Item1").initialPrice(150.75).build();
        var item2 = Item.builder().itemName("Item2").initialPrice(250.75).build();
        var item3 = Item.builder().itemName("Item3").initialPrice(350.75).build();

        itemRepository.saveAll(List.of(item1, item2, item3));

    }

    public List<ItemDTO> getAllItems() {
        log.info("getAllItems processing");
        return itemRepository.findAll()
                             .stream()
                             .map(item -> new ItemDTO(item.getId(),
                                                      item.getItemName(),
                                                      item.getInitialPrice()))
                             .collect(Collectors.toList());
    }

}
