package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void homeReturnsAppInfo() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app").value("demotest"));
    }

    @Test
    void helloUsesTheNameParameter() throws Exception {
        mvc.perform(get("/hello").param("name", "Ali"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Ali!"));
    }

    @Test
    void pingIsOk() throws Exception {
        mvc.perform(get("/api/ping"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }
}
