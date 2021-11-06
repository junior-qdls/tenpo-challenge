package com.challenge.tenpo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.challenge.tenpo.repository.UserRepository;

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
public class LoginTest {
    @Resource
    private WebApplicationContext context;

    @Resource
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        userRepository.deleteAll();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
        mockMvc.perform(post("/api/v1/users/signup").contentType("application/json")
                .content("{\"email\":\"juniorquerol@gmail.com\",\"userName\":\"juniorquerol@gmail.com\",\"password\":\"Peru123\",\"firstName\":\"junior\",\"lastName\":\"querol\"}"));
    }

    @Test
    public void adminCanLog() throws Exception {
        mockMvc
                .perform(formLogin().user("juniorquerol@gmail.com").password("Peru123"))
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("juniorquerol@gmail.com"));

        mockMvc
                .perform(logout())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?logout"));
    }


    @Test
    public void invalidLoginDenied() throws Exception {
        String loginErrorUrl = "/login?error";
        mockMvc
                .perform(formLogin().password("invalid"))
                .andExpect(status().is4xxClientError())
                .andExpect(unauthenticated());
    }
}
