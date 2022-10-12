package com.luxoft.sb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.luxoft.sb.dao.TrainerRepoistory;
import com.luxoft.sb.entity.Trainer;
import com.luxoft.sb.exception.ResourceNotFoundException;

class TrainerServiceImplTest {
	@InjectMocks
	private TrainerServiceImpl trainerService;
	
	@Mock
	private TrainerRepoistory trainerRepo;
	
	private MockMvc mockMvc;
	
	Trainer trainer = new Trainer(1,"ishrat","password");
	Trainer trainerData = new Trainer(1,"ishrat","updatepass");

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(trainerService).build();
	}

	@Test
	void testUpdatePass() {
		Mockito.when(trainerRepo.findById(anyInt())).thenReturn(Optional.of(trainer));
		Mockito.when(trainerRepo.save(any())).thenReturn(trainerData);
		Trainer updatePass = trainerService.updatePass(trainerData,1);
		assertEquals(trainerData.getPassword(),updatePass.getPassword());
	}
	
	@Test
	void testUpdatePassException() {
		Mockito.when(trainerRepo.findById(anyInt())).thenThrow(ResourceNotFoundException.class);
		try {
			trainerService.updatePass(trainerData,1);
		}catch(Exception e) {
			assertEquals(ResourceNotFoundException.class,e.getClass());
		}
	}

}
