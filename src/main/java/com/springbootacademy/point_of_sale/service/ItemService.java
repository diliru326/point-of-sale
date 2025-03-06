package com.springbootacademy.point_of_sale.service;

import com.springbootacademy.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.point_of_sale.dto.response.PaginatedItemsResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName);

    List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activestate);

    PaginatedItemsResponseDTO getItemsByActiveStatusWithPaginated(boolean activestate, int page, int size);
}
