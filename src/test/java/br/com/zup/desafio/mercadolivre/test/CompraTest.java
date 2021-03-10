package br.com.zup.desafio.mercadolivre.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class CompraTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Deveria retornar ")
	@WithMockUser("a@gmail.com")
	public void deveria () throws Exception{
		
		String request = "{\r\n"
				+ "    \"idTransacao\":1,\r\n"
				+ "    \"status\":1\r\n"
				+ "}";
		
	      mockMvc.perform(MockMvcRequestBuilders.post("/retorno-pagseguro/1")
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(request)
	        ).andExpect(MockMvcResultMatchers.status().is(200))
	                .andDo(MockMvcResultHandlers.print());
		
	}
	
	
	

}
