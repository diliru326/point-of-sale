package com.springbootacademy.point_of_sale.controller;

import com.springbootacademy.point_of_sale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.point_of_sale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.point_of_sale.dto.response.PaginatedResponseOrderDetails;
import com.springbootacademy.point_of_sale.service.OrderService;
import com.springbootacademy.point_of_sale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        String messege = orderService.saveOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(
                200, messege, requestOrderSaveDTO),
                HttpStatus.CREATED);
    }

    @GetMapping(
            params = {"stateType", "page", "size"},
            path = "get-order-details"
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseOrderDetails p = null;
        if (stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")) {
            // if active then take it as true. or else take it as false. this is called ternary operator
            boolean status = stateType.equalsIgnoreCase("active") ? true : false;
            p = orderService.getAllOrderDetails(status, page, size);
        }
        return new ResponseEntity<StandardResponse>(new StandardResponse(
                201, "SUCCESS", p
        ), HttpStatus.OK);
    }

}
