package com.quickgrab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quickgrab.model.RestaurantModel;

@Repository
public interface RestaurantRepo extends JpaRepository<RestaurantModel, Integer> {

}
