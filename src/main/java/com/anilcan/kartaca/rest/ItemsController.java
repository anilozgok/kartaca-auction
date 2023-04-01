package com.anilcan.kartaca.rest;

import com.anilcan.kartaca.model.response.ItemListResponse;
import com.anilcan.kartaca.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemsController {

    private final ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<ItemListResponse> getItems() {
        log.info("getItems caught.");
        return new ResponseEntity<>(new ItemListResponse(itemService.getAllItems()), HttpStatus.OK);
    }

}
