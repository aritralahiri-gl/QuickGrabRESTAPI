package com.quickgrab.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.ToString;

@AllArgsConstructor

@NoArgsConstructor

@Data
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class RestaurantOrder {

 private int quantity;

 private float orderPrice;

 private int custId;

 private String foodName;

 private float foodPrice;

}