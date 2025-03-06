package com.springbootacademy.point_of_sale.repo;

import com.springbootacademy.point_of_sale.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(basePackageClasses = Item.class)
public interface ItemRepo extends JpaRepository<Item, Integer> {
    List<Item> findAllByItemNameEqualsAndActivestateEquals(String itemName, Boolean b);

    List<Item> findAllByActivestateEquals(boolean activestate);

    Page<Item> findAllByActivestateEquals(boolean activestate, Pageable pageable);

    long countAllByActivestateEquals(boolean activestate);
}
