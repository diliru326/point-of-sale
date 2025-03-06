package com.springbootacademy.point_of_sale.repo;

import com.springbootacademy.point_of_sale.dto.querryInterfaces.OrderDetailsInterface;
import com.springbootacademy.point_of_sale.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(basePackageClasses = Order.class)
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(value = "select c.customer_name as customerName, c.customer_address as customerAddress ," +
            " c.contact_numbers as contactNumbers , o.order_date as date, o.total as total from customer c, orders o where o.active_state = ?1 and c.customer_id = o.customer_id",nativeQuery = true)
    List<OrderDetailsInterface> getAllOrderDetails(boolean status, Pageable pageable);

    @Query(value = "select count(*) from customer c, orders o where o.active_state = ?1 and c.customer_id = o.customer_id ",nativeQuery = true)
    long countAllOrderDetails(boolean status);
}
