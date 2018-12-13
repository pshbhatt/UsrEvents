package com.motel.usrEvnts;

import com.motel.usrEvnts.entity.UserEvents;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsrEvntsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@MockBean
	UserEventRepository repo;


	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAddUserEvent() throws Exception {
		UserEvents userEvents = new UserEvents();
		userEvents.setType("type");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		userEvents.setTimestamp(timestamp);
		when(repo.save(userEvents))
				.thenReturn(userEvents);
		mockMvc.perform(MockMvcRequestBuilders.post("/userEvent/addEvent"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		verify(repo, times(1)).save(isA(UserEvents.class));
	}

	@Test
	public void testGetByTimestamp() throws Exception {
		UserEvents userEvents = new UserEvents();
		List<UserEvents> usrList = new ArrayList<>();
		userEvents.setType("type");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		userEvents.setTimestamp(timestamp);
		when(repo.findByTimestamp(timestamp))
				.thenReturn(usrList);
		mockMvc.perform(MockMvcRequestBuilders.get("/userEvent/timestamp/"+timestamp))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		verify(repo, times(1)).findByTimestamp(timestamp);
	}

	@Test
	public void testGetByUserId() throws Exception {
		UserEvents userEvents = new UserEvents();
		List<UserEvents> usrList = new ArrayList<>();
		userEvents.setType("type");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		userEvents.setTimestamp(timestamp);
		when(repo.findByUserId(1111L))
				.thenReturn(usrList);
		mockMvc.perform(MockMvcRequestBuilders.post("/userEvent/addEvent"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		verify(repo, times(1)).findByUserId(1111L);
	}

}

