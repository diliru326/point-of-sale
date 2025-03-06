package com.springbootacademy.point_of_sale.repo;

import com.springbootacademy.point_of_sale.entity.Order;
import com.springbootacademy.point_of_sale.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories(basePackageClasses = OrderDetails.class)
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {
}
