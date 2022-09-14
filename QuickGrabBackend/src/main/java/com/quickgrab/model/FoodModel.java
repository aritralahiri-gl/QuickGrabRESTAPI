package com.quickgrab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Food")
public class FoodModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private int foodId;
	@Column(name = "food_name")
	private String foodName;
	@Column(name = "food_price")
	private float foodPrice;
	@Column(name = "food_type")
	private String food_type;
	@Column(name = "rest_id")
	private Integer restId;
//	@Column(name = "order_id")
//	private Integer orderId;


}
