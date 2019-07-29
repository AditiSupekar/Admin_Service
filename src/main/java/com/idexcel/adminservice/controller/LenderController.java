package com.idexcel.adminservice.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.idexcel.adminservice.dto.AdminDTO;
import com.idexcel.adminservice.dto.PatchDTO;
import com.idexcel.adminservice.entity.Lender;

import com.idexcel.adminservice.service.impl.LenderServiceImpl;

@RestController
@RequestMapping("/api")
public class LenderController {
	
	
	
	LenderServiceImpl lenderServiceImpl;
	
	@Autowired
	public LenderController(LenderServiceImpl lenderServiceImpl) {
		this.lenderServiceImpl = lenderServiceImpl;
	}

	AdminDTO adminDTO = new AdminDTO();
	PatchDTO patchDTO = new PatchDTO();
		
	
	
	@GetMapping("/lenders")
	public List<Lender> getLenders(){
		return lenderServiceImpl.getLenders();
	}

	@GetMapping("/books")
	public ResponseEntity<String> getBooks(){
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl  = "https://jsonplaceholder.typicode.com/todos/";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
		return response;
	}
	
	@GetMapping("/lenders/{lenderId}")
	public Lender getLenderId(@PathVariable String lenderId){
		Lender theLender = lenderServiceImpl.getLenderId(lenderId);
		return theLender;
	}
	

	@PostMapping("/lenders")
	public Lender addLender(@RequestBody Lender theLender) {
		lenderServiceImpl.saveLender(adminDTO); //SPECIFY dto	
		return theLender;
	}
	
	@PutMapping("/lenders/{lenderId}")
	public void updatebyId (@RequestBody Lender theLenders, @PathVariable String _id ) {
		
		this.lenderServiceImpl.updateById(theLenders,_id); 
		
	}
	
	@DeleteMapping("/lenders/{lenderId}")
	public void deleteById (@PathVariable String lenderId) {
		
		this.lenderServiceImpl.deleteById(lenderId);
	}
	
	@PatchMapping("/lenders/{lenderId}/status")
	public void updateStatus(@RequestBody  PatchDTO patchDTO, @PathVariable String _id) {
		
		this.lenderServiceImpl.updateStatus(patchDTO, _id);
	}
	
	
	@RequestMapping(value= "/lenders/{LenderId}", method = RequestMethod.HEAD)
	
	public ResponseEntity <String> returnHeader(@PathVariable String LenderId) {
		
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.set("Admin-Service-Header", "Contains the Lender Information");
		ResponseEntity <String> theResponseEntity = new ResponseEntity<String>("Header Information of the Admin Servie", responseHeader, HttpStatus.OK);
		
		if (lenderServiceImpl.checkLender(LenderId))	{		
			
			return theResponseEntity;
		}
		return theResponseEntity;	
		
		 
	}
	
	@GetMapping("/infofromtodo")
	public ResponseEntity <String> getnfoFormRest(){
		
		RestTemplate restTemplate = new RestTemplate ();
		String todosURL = "https://jsonplaceholder.typicode.com/todos";
		
		ResponseEntity<String> response = restTemplate.getForEntity(todosURL + "/1", String.class);
		
		return response;
		
		
	}

}
