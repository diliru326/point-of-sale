package com.springbootacademy.point_of_sale.controller;

import com.springbootacademy.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.point_of_sale.dto.response.PaginatedItemsResponseDTO;
import com.springbootacademy.point_of_sale.service.ItemService;
import com.springbootacademy.point_of_sale.util.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @PostMapping("/save")
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
//        String message = itemService.saveItem(itemSaveRequestDTO);
//        return message;
//    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message = itemService.saveItem(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(
                200,"saved sucessfully",message),
                HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItmByNameAndStatus(@RequestParam(value = "name") String itemName ){
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName);
        return itemDTOS;
    }

    @GetMapping(
            path = "/get-by-name-by-mapstruct",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItmByNameAndStatusByMapstruct(@RequestParam(value = "name") String itemName ){
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusByMapstruct(itemName);
        return itemDTOS;
    }

    @GetMapping(
        path = "get/all/item-by-status",
        params = "activeStatus"
    )
        public ResponseEntity<StandardResponse> getItemsByActiveStatus(@RequestParam(value = "activeStatus") boolean activestate ){

        List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemsByActiveStatus(activestate);
        return new ResponseEntity<StandardResponse>(new StandardResponse(
                201,"Items found with given state",itemGetResponseDTOS),
                HttpStatus.OK);
    }

    // below is for the pagination part

    @GetMapping(
            path = "get/all/item-by-status",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemsByActiveStatusWithPaginated(
            @RequestParam(value = "activeStatus") boolean activestate,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){

        //List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemsByActiveStatus(activestate);
        PaginatedItemsResponseDTO paginatedItemsResponseDTO = itemService.getItemsByActiveStatusWithPaginated(activestate,page,size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(
                201,"Items found with given state",paginatedItemsResponseDTO),
                HttpStatus.OK);
    }
}
