package com.example.pizzas.controllers;

import com.example.pizzas.entities.Pizza;
import com.example.pizzas.repositories.PizzaRepository;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PizzaController.class)
class PizzaMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaRepository pizzaRepository;

    @Test
    void getAllPizzasReturnsOnePizza() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pizzas"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void addPizzaAndExceptOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Visuvio\", \"price\": 70 }"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void addPizzaAndExceptERROR() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().is4xxClientError());
    }
}