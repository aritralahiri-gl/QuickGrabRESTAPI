package com.quickgrab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quickgrab.model.OrderModel;

@Repository
public interface OrderRepo extends JpaRepository<OrderModel, Integer> {

}
