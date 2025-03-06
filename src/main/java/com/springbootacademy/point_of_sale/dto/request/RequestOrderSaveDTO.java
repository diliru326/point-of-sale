package com.springbootacademy.point_of_sale.dto.request;

import com.springbootacademy.point_of_sale.entity.Customer;
import com.springbootacademy.point_of_sale.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private Date date;

    private double total;

    private int customer;

    private List<RequestOrderDetailsSave> orderDetails;
}
