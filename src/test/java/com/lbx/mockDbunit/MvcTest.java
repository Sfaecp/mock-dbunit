package com.lbx.mockDbunit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class MvcTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	/**
	 * 初始化，必须有
	 */
	@Before 
    public void setUp() {  
		// 实例化方式一
//		mockMvc = MockMvcBuilders.standaloneSetup(new AccountManageController()).build();
				// 实例化方式二
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		//MockitoAnnotations.initMocks(this);  
    } 
	
	@Test
	public void testAcctController() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/qryAllAccountType")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andReturn();
		
		System.out.println(mvcResult.getResponse().getContentAsString());
	}
}
