package com.seb.anime.mvc.controller;

import com.seb.anime.AbstractSpringMVCTest;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.w3c.dom.Node;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by efreseb on 27/03/2017.
 */
@Ignore
public class HomeControllerTest extends AbstractSpringMVCTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private ResultActions authenticationResult;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        authenticationResult = mvc.perform(formLogin("/login").user("cristo").password("123456"));
    }

    @After
    public void after() throws Exception {
        mvc.perform(logout());
    }

    @Test
    public void testAuthentication() throws Exception {
        authenticationResult.andExpect(authenticated());
    }

    @Test
    public void testInvalidCsrf() throws Exception {
        mvc.perform(post("/").with(csrf()
                .useInvalidToken())).andExpect(status().isForbidden());
    }

    @Test
    public void testHome() throws Exception {
        mvc.perform(post("/").with(csrf())).andExpect(status().isPermanentRedirect());
    }

    @Test
    public void testSubmitCode() throws Exception {
        mvc.perform(post("/submitCode").with(csrf()))
                .andExpect(status().isPermanentRedirect())
                .andExpect(content().contentType("text/html"))
                .andExpect(content().node(new BaseMatcher<Node>() {
                    @Override
                    public void describeTo(Description description) {
                        System.out.println(description);
                    }

                    @Override
                    public boolean matches(Object o) {
                        System.out.println(o);
                        return false;
                    }
                }));

    }

}
