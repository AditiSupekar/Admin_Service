package com.idexcel.adminservice;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.idexcel.adminservice.controller.LenderController;
import com.idexcel.adminservice.dto.AdminDTO;
import com.idexcel.adminservice.dto.PatchDTO;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.entity.LenderAddress;
import com.idexcel.adminservice.entity.LenderStatus;
import com.idexcel.adminservice.service.impl.LenderServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(LenderController.class)
public class ControllerTest {
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	private LenderServiceImpl lenderServiceImpl;
	
	@Test
	public void GetId() throws Exception {
		
		String _id = "12345678";
		when(lenderServiceImpl.getLenderId(_id)).thenReturn(new Lender("12345678", "Commercia Bank", new LenderAddress("459 Herndon Parkway", "Ashburn", "VA","20148", "USA"), LenderStatus.Active, "Praveen K", "Sun Jun 09 03:11:17 EDT 2019", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019"));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/lenders/12345678");
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				//.andExpect(content().json("{\"_id\":\"12345678\",\"name\":\"Commercia Bank\",\"address\":{\"street\":\"459 Herndon Parkway\",\"city\":\"Ashburn\",\"state\":\"VA\",\"zipcode\":\"20148\",\"country\":\"USA\"},\"status\":\"Active\",\"createdBy\":\"Praveen K\",\"createdDate\":\"Sun Jun 09 03:11:17 EDT 2019\",\"updatedBy\":\"Praveen K\",\"updatedDate\":\"Sun Jun 09 03:11:17 EDT 2019\"}"))
				.andReturn();
	}
	
	@Test
	public void testPost() throws Exception {
		
		AdminDTO adminServiceDto = new AdminDTO("City Bank", new LenderAddress("12853 Elden Street", "Herndon","VA","20171","USA"));
		when(lenderServiceImpl.saveLender(adminServiceDto)).thenReturn("5768542");
		
		RequestBuilder request = MockMvcRequestBuilders.post("/api/lenders").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
		
	}
	
	@Test
	public void PutMapping() throws Exception {
		
		String _id = "12345678";
		Lender theLender = new Lender ("12345678", "Commercia Bank", new LenderAddress("459 Herndon Parkway", "Ashburn", "VA","20148", "USA"),LenderStatus.NotActive, "Praveen K", "Sun Jun 09 03:11:17 EDT 2019", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019");
		lenderServiceImpl.updateById(theLender, _id);
		
		RequestBuilder request = MockMvcRequestBuilders.put("/api/lenders/12345678").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
			   .andExpect(status().isBadRequest())
			   .andReturn();
	}
	
	@Test
	public void DeleteId() throws Exception {
		
		String _id = "5768542";
		lenderServiceImpl.deleteById(_id);
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/lenders/5768542");
		
		mockMvc.perform(request)
		   .andExpect(status().isOk())
		   .andReturn();
	
	}
	
	@Test
	public void patchId() throws Exception{
		String _id = "12345678";
		PatchDTO theLendersPatchDto = new PatchDTO ("12345678", "Not Active");
		lenderServiceImpl.updateStatus(theLendersPatchDto, _id);
		
		
		RequestBuilder request = MockMvcRequestBuilders.patch("/api/lenders/5768542/status");
				
		mockMvc.perform(request)
			   .andExpect(status().isBadRequest())
			   .andReturn();
	}


}
