package com.ipnetinstitute.csc394.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ipnetinstitute.csc394.backend.entity.User;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")

class Csc394backendApplicationTests {

	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation)).build();
	}

	@Test
	@Order(1)
	public void saveTest() throws JsonProcessingException, Exception {
		System.out.println("SaveTest is called");
		User user = new User("John", "Doe", "+228 90 12 13 14", "john.doe@gmail.com");
		user.setModBy(1);
		user.setUserName("john.doe");
		user.setPassword("secret@Password");
		user.setModDateTime(new Date());
		user.setCreateDateTime(new Date());
		System.out.println(user);
		String userJson = mapper.writeValueAsString(user);
		System.out.println(userJson);
		try {
			mockMvc.perform(post("/save/{entity}", "user").contentType(MediaType.APPLICATION_JSON).content(userJson))
					.andExpect(status().isOk()).andExpect(jsonPath("userName", is(user.getUserName())))
					.andDo(document("save", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("User id").ignored(),
									fieldWithPath("userName").description("Nom d'utilisateur"),
									fieldWithPath("password").description("Mot de passe"),
									fieldWithPath("firstName").description("Prenom"),
									fieldWithPath("lastName").description("Nom"),
									fieldWithPath("phone").description("Telephone"),
									fieldWithPath("eMail").description("E-mail"),
									fieldWithPath("type").description("Set this to user"),
									fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"))));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Test
	@Order(2)
	public void loginTest() throws JsonProcessingException, Exception {
		System.out.println("loginTest is called");
		User user = new User();
		user.setUserName("john.doe");
		user.setPassword("secret@Password");
		System.out.println(user);
		String userJson = mapper.writeValueAsString(user);
		System.out.println(userJson);
		try {
			mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
					.andExpect(status().isOk())
					.andExpect(jsonPath("userName", is(user.getUserName())))
					.andDo(document("login", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("User id").ignored(),
									fieldWithPath("userName").description("Nom d'utilisateur"),
									fieldWithPath("password").description("Mot de passe"),
									fieldWithPath("firstName").description("Prenom").ignored(),
									fieldWithPath("lastName").description("Nom").ignored(),
									fieldWithPath("phone").description("Telephone").ignored(),
									fieldWithPath("eMail").description("E-mail").ignored(),
									fieldWithPath("type").description("Set this to user").ignored(),
									fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee").ignored())));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Test
	@Order(3)
	public void deleteUserTest() throws JsonProcessingException, Exception {
		System.out.println("delete is called");
		try {
			mockMvc.perform(post("/deleteUser").contentType(MediaType.APPLICATION_JSON)
					.content("john.doe")).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteUser",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())
							//requestFields(fieldWithPath("content").description("User name"))
							//responseFields(fieldWithPath("content").description("Success or Error: Error message"))
							));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Test
	@Order(4)
	public void countTest() throws JsonProcessingException, Exception {
		System.out.println("Count is called");
		try {
			mockMvc.perform(get("/count/{entity}", "user").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andDo(document("count",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

}
