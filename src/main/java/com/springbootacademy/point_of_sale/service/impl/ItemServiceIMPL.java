package com.springbootacademy.point_of_sale.service.impl;

import com.springbootacademy.point_of_sale.dto.ItemDTO;
import com.springbootacademy.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.point_of_sale.dto.response.PaginatedItemsResponseDTO;
import com.springbootacademy.point_of_sale.entity.Item;
import com.springbootacademy.point_of_sale.exception.NotFoundException;
import com.springbootacademy.point_of_sale.repo.ItemRepo;
import com.springbootacademy.point_of_sale.service.ItemService;
import com.springbootacademy.point_of_sale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//        Item item = new Item(
//                1,
//                itemSaveRequestDTO.getItemName(),
//                itemSaveRequestDTO.getMeasuringunitType(),
//                itemSaveRequestDTO.getBalanceQty(),
//                itemSaveRequestDTO.getSupplierPrice(),
//                itemSaveRequestDTO.getSellingPrice(),
//                true
//        );
//        itemRepo.save(item);
//        return "Item Saved " + itemSaveRequestDTO.getItemName();
//    }
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        //by default the item state will be false. but if want true
        // item.setactivestate(true);
        if (!itemRepo.existsById(item.getId())) {
            itemRepo.save(item);
        } else {
            throw new DuplicateKeyException("Item already exists");
        }
        return "Item Saved " + itemSaveRequestDTO.getItemName();
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean b = true;
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActivestateEquals(itemName, b);
        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTO = modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>() {
            }.getType());

            return itemGetResponseDTO;
        } else {
            throw new RuntimeException("No item found with name " + itemName);
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName) {
        boolean b = true;
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActivestateEquals(itemName, b);
        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTO = itemMapper.entityListToDtoList(items);

            return itemGetResponseDTO;
        } else {
            throw new RuntimeException("No item found with name " + itemName);
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activestate) {

        List<Item> items = itemRepo.findAllByActivestateEquals(activestate);
        if (items.size() > 0) {

            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;
        } else {
            throw new NotFoundException("No item found with active status " + activestate);
        }
    }

    @Override
    public PaginatedItemsResponseDTO getItemsByActiveStatusWithPaginated(boolean activestate, int page, int size) {
        Page<Item> items = itemRepo.findAllByActivestateEquals(activestate , PageRequest.of(page,size));
        long count = itemRepo.countAllByActivestateEquals(activestate);
        if (items.getSize()<1){
            throw new NotFoundException("No item found with active status " + activestate);
        }else{
            PaginatedItemsResponseDTO paginatedItemsResponseDTO = new PaginatedItemsResponseDTO(
                    itemMapper.itemlistToDTOList (items),count
            );
            return paginatedItemsResponseDTO;
        }


    }


}
