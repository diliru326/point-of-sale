package com.springbootacademy.point_of_sale.service.impl;

import com.springbootacademy.point_of_sale.dto.querryInterfaces.OrderDetailsInterface;
import com.springbootacademy.point_of_sale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.point_of_sale.dto.response.PaginatedResponseOrderDetails;
import com.springbootacademy.point_of_sale.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.point_of_sale.entity.Order;
import com.springbootacademy.point_of_sale.entity.OrderDetails;
import com.springbootacademy.point_of_sale.repo.CustomerRepo;
import com.springbootacademy.point_of_sale.repo.ItemRepo;
import com.springbootacademy.point_of_sale.repo.OrderDetailsRepo;
import com.springbootacademy.point_of_sale.repo.OrderRepo;
import com.springbootacademy.point_of_sale.service.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    @Transactional
    public String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal(),
                customerRepo.getById(requestOrderSaveDTO.getCustomer())
        );
        orderRepo.save(order);

        if (orderRepo.existsById(order.getId())) {
            List<OrderDetails> orderDetails = modelMapper.map(
                    requestOrderSaveDTO.getOrderDetails(),
                    new TypeToken<List<OrderDetails>>(){}.getType());

//            check that the order details entity have a order property. but requestOrderDetailsSave
//            does not have that property. so we have to manually set that. Items also must be saved as a item type.


                for (int i = 0 ;  i<orderDetails.size(); i++) {
                    orderDetails.get(i).setOrders(order);
                    orderDetails.get(i).setItems(itemRepo.getById(
                            requestOrderSaveDTO.getOrderDetails().get(i).getItems()
                    ));
                }

            if (orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }
        }
        return "Order saved successfully";
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        //if data coming from 2 or more tables creatre a interface class like below.
        // and add get or set methods according to the properties
        List <OrderDetailsInterface> orderDetailsDTOS = orderRepo.getAllOrderDetails(status, PageRequest.of(page,size));

        // ResponseOrderDetailsDTO is inside the PaginatedResponseOrderDetails DTO.
        List<ResponseOrderDetailsDTO> list = new ArrayList<>();
        for (OrderDetailsInterface o : orderDetailsDTOS ){
            ResponseOrderDetailsDTO r = new ResponseOrderDetailsDTO(
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getContactNumbers(),
                    o.getDate(),
                    o.getTotal()
            );
            list.add(r);
        }
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
                list , orderRepo.countAllOrderDetails(status)
        );
        return paginatedResponseOrderDetails;
    }
}
