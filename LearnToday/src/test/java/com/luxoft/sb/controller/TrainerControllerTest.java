package com.luxoft.sb.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.sb.dao.TrainerRepoistory;
import com.luxoft.sb.entity.Trainer;
import com.luxoft.sb.exception.ResourceNotFoundException;
import com.luxoft.sb.service.TrainerService;

class TrainerControllerTest {

	@Mock
	private TrainerService trainerService;
	
	@Mock
	private TrainerRepoistory trainerRepo;
	
	@InjectMocks
	private TrainerController trainerController;
	
	private MockMvc mockMvc;
	
	Trainer trainer = new Trainer(1001,"ishrat","pass");
	Trainer trainerData = new Trainer(1001, "ishrat", "upass");
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();
	}

	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objMap = new ObjectMapper();
		return objMap.writeValueAsString(object);
	}
	
//	@Test
//	void testSignupUser() throws Exception {
//		Mockito.when(trainerRepo.save(any())).thenReturn(trainer);
//		String content = mapToJson(trainerData);
//		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/Trainer/signUp").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
//		mockMvc.perform(requestBuilder).andExpect(status().isCreated());
//	}

	@Test
	void testUpdatePass() throws Exception {
		Mockito.when(trainerRepo.save(any())).thenReturn(trainer);
		String content = mapToJson(trainerData);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/Trainer/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
	@Test
	void testUpdatePasswordException() {
		Mockito.when(trainerRepo.save(any())).thenThrow(ResourceNotFoundException.class);
		try {
			String content = mapToJson(trainerData);
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/Trainer/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
			mockMvc.perform(requestBuilder).andExpect(status().isOk());
		}catch(Exception e) {
			assertEquals(ResourceNotFoundException.class,e.getClass());
		}
	}

}
