package com.quickgrab.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Customer")

public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private int custId;
	@Column(name = "cust_name")
	private String custName;
	@Column(name = "cust_pass")
	private String custPass;
	@Column(name = "cust_address")
	private String custAddress;
	@Column(name = "cust_contact")
	private String custContact;
	@Column(name = "cust_email")
	private String custEmail;
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id")
	private List<OrderModel> orderModel;
	

}
