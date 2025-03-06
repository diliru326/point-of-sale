package com.springbootacademy.point_of_sale.entity;

import com.springbootacademy.point_of_sale.entity.enums.MeasuringUnitType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @Column(name = "order_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private Boolean activestate;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;

    public Order(Date date, double total, Customer customer) {
        this.date = date;
        this.total = total;
        this.customer = customer;
    }
}
