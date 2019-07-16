package com.idexcel.adminservice.dto;



import com.idexcel.adminservice.entity.LenderAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class AdminDTO {
	
	private String name;
	
	private LenderAddress address;
	

}
