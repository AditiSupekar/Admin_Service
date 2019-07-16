package com.idexcel.adminservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class LenderAddress {
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	}

