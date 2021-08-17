package com.example.pizzas.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class pizzaControllerSpringBootTest {

    @Autowired
    private PizzaController pizzaController;

    @Test
    void getAllPizzas() {
        assertThat(pizzaController.getAllPizzas()).isEmpty();
    }
}
