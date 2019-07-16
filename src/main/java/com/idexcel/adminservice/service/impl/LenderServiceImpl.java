package com.idexcel.adminservice.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import  com.idexcel.adminservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.entity.LenderStatus;
import com.idexcel.adminservice.exception.LenderAlreadyExistException;
import com.idexcel.adminservice.service.LenderService;
import com.idexcel.adminservice.DAO.LenderDAO;
import com.idexcel.adminservice.exception.*;
import com.idexcel.adminservice.dto.AdminDTO;
import com.idexcel.adminservice.dto.PatchDTO;

@Service
public class LenderServiceImpl implements LenderService {
	
	
	private LenderDAO lenderDAO;
	
	@Autowired
	public LenderServiceImpl(LenderDAO lenderDAO) {
		this.lenderDAO = lenderDAO;
	}
	
	
	ModelMapperDTO theModelMapper = new ModelMapperDTO(); 
	
	
	
	public List<Lender> getLenders() {
		return lenderDAO.findAll();
	}
	
	public Lender getLenderId(String theId) {
		
		Optional<Lender> lender = this.lenderDAO.findById(theId);
		if(lender == null) {
		}
		Lender len = lender.get();
		return len;
	}
	
	public String saveLender(AdminDTO adminDTO) {
		
		String name = adminDTO.getName();
		
		System.out.println(name);
		
		if (lenderDAO.findByName(name)==null){				
		Lender theLenders = theModelMapper.converttoEntity(adminDTO);
		theLenders.setId("12345678");
		theLenders.setStatus(LenderStatus.Active);
		theLenders.setCreatedBy("Praveen K");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); 
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		theLenders.setCreatedDate(nowAsISO);
		theLenders.setUpdatedBy("Praveen K");
		theLenders.setUpdatedDate(nowAsISO);
		
		lenderDAO.insert(theLenders);
		
		return theLenders.getId();
		
		} else {
			throw new LenderAlreadyExistException ("lender Exist" + name);
		}

	}

	public void updateById(Lender theLenders, String _id) {
		this.lenderDAO.save(theLenders);
	}

	public void deleteById(String _id) {
		this.lenderDAO.deleteById(_id);
		
	}
public void updateStatus(PatchDTO PatchDTO, String _id) {
		
		if (lenderDAO.existsById(_id)) {
		Lender theLenders = this.lenderDAO.findById(_id).orElse(null);
		
		theLenders.setStatus(LenderStatus.Active);	
		this.lenderDAO.save(theLenders);
		}
		
		else
			throw new LenderNotFoundException ("The Lender with Id:"+ _id +"does not exist");
	}
	
	
	public ResponseEntity<String> getMetaData(String _id) {
		
		if (lenderDAO.existsById(_id)) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public boolean checkLender(String _id) {
		if (lenderDAO.existsById(_id))
			return true;
		
		else
			throw new LenderNotFoundException ("The Lender with Id:"+ _id +"do not exist" );
			
	}

	@Override
	public Lender addLender(Lender theLender) {
		return null;
	}
}
