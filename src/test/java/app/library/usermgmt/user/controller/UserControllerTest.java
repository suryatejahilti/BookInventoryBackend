package app.library.usermgmt.user.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import app.library.usermgmt.models.AuthenticationRequest;
import app.library.usermgmt.models.AuthenticationResponse;
import app.library.usermgmt.models.RegistrationRequest;
import app.library.usermgmt.user.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;


    @Test
    void testLoginUser() throws Exception {
        when(userServiceImpl.loginUser((AuthenticationRequest) any()))
                .thenReturn(new AuthenticationResponse("ABC123", "Roles", "Name"));

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setPassword("iloveyou");
        authenticationRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"accessToken\":\"ABC123\",\"roles\":\"Roles\",\"name\":\"Name\"}"));
    }


    @Test
    void testRegisterUser() throws Exception {
        when(userServiceImpl.registerUser((RegistrationRequest) any()))
                .thenReturn(new AuthenticationResponse("ABC123", "Roles", "Name"));

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setName("Name");
        registrationRequest.setPassword("iloveyou");
        registrationRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registrationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"accessToken\":\"ABC123\",\"roles\":\"Roles\",\"name\":\"Name\"}"));
    }
}

