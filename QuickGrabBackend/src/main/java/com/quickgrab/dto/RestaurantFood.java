package com.quickgrab.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data

@AllArgsConstructor

@NoArgsConstructor

@ToString

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class RestaurantFood {

	private String restName;

	private String restAddress;

	private String restContact;

	private String foodName;

	private float foodPrice;

}
