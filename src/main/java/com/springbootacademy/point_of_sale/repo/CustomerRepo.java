package com.springbootacademy.point_of_sale.repo;

import com.springbootacademy.point_of_sale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByActiveEquals(boolean active);
}

