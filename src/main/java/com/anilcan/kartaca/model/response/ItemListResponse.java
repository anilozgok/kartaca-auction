package com.anilcan.kartaca.model.response;

import com.anilcan.kartaca.model.dto.ItemDTO;

import java.util.List;

public record ItemListResponse(List<ItemDTO> items) {

}
