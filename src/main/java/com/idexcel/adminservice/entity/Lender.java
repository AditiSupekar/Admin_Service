package com.idexcel.adminservice.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Document(collection="Lenders")
public class Lender {
	@Id
	private String id;
	private String name;
	private LenderAddress address;
	private LenderStatus status;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	
}