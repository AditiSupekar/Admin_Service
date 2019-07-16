package com.idexcel.adminservice.dto;

import org.modelmapper.ModelMapper;

import com.idexcel.adminservice.entity.Lender;

public class ModelMapperDTO {

	ModelMapper modelMapper = new ModelMapper();
	
	public AdminDTO convertToDTO (Lender lenders) {
		
		AdminDTO adminServiceDTO = modelMapper.map(lenders, AdminDTO.class);
		
		return adminServiceDTO;
		
	}
	
	public Lender converttoEntity (AdminDTO adminDTO) {
		
		Lender theLenders = modelMapper.map(adminDTO, Lender.class);
		
		return theLenders;
	}
	
	public Lender convertPatchToEntity (PatchDTO patchDTO) {
				
		Lender theLenders = modelMapper.map(patchDTO, Lender.class);
				
		return theLenders;
	}
	
	public GetDTO convertEntitytoGetAll(Lender theLenders) {
		
		GetDTO getDTO = modelMapper.map(theLenders, GetDTO.class);
		
		return getDTO;
	}

}
