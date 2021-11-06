package com.challenge.tenpo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.challenge.tenpo.repository.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Resource
    private WebApplicationContext context;

    @Resource
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        userRepository.deleteAll();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void signUpSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/users/signup").contentType("application/json")
                        .content("{\"email\":\"juniorquerol@gmail.com\",\"userName\":\"juniorquerol@gmail.com\",\"password\":\"Peru123\",\"firstName\":\"junior\",\"lastName\":\"querol\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User has been created")));
    }

    @Test
    public void signUpError() throws Exception {
        mockMvc.perform(post("/api/v1/users/signup").contentType("application/json")
                .content("{\"email\":\"juniorquerol@gmail.com\",\"userName\":\"juniorquerol@gmail.com\",\"password\":\"Peru123\",\"firstName\":\"junior\",\"lastName\":\"querol\"}"));
        mockMvc.perform(post("/api/v1/users/signup").contentType("application/json")
                .content("{\"email\":\"juniorquerol@gmail.com\",\"userName\":\"juniorquerol@gmail.com\",\"password\":\"Peru123\",\"firstName\":\"junior\",\"lastName\":\"querol\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("UserName already exists")));
    }

}
