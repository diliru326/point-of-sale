package com.springbootacademy.point_of_sale.dto;

import com.springbootacademy.point_of_sale.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {

    private int id;

    private String itemName;

    private MeasuringUnitType measuringunitType;

    private double balanceQty;

    private double supplierPrice;

    private double sellingPrice;

    private boolean activestate;
}
