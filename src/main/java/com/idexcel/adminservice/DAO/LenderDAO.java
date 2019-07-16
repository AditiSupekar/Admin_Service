package com.idexcel.adminservice.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.idexcel.adminservice.entity.Lender;

@Repository
public interface LenderDAO extends MongoRepository<Lender, String>{
	
	public Lender findByName (String name);
	
}
