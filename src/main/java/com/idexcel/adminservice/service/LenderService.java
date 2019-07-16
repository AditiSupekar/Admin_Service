package com.idexcel.adminservice.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.idexcel.adminservice.dto.AdminDTO;
import com.idexcel.adminservice.dto.PatchDTO;
import com.idexcel.adminservice.entity.Lender;

public interface LenderService {
	
	public List<Lender> getLenders();
	
	public Lender getLenderId(String theId);
	
	String saveLender(AdminDTO adminDto);
	
	public Lender addLender(@RequestBody Lender theLender);
	
	public void updateById(Lender theLenders, String _id);
	
	public void deleteById(String _id);
	
	public void updateStatus(PatchDTO PatchDTO, String _id);

}
