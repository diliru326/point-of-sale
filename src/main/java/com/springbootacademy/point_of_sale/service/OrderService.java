package com.springbootacademy.point_of_sale.service;

import com.springbootacademy.point_of_sale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.point_of_sale.dto.response.PaginatedResponseOrderDetails;

public interface OrderService {
    String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size);
}
