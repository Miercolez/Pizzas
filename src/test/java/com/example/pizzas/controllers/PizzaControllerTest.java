package com.example.pizzas.controllers;

import com.example.pizzas.repositories.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*Detta test behövs inte*/
@ExtendWith(MockitoExtension.class)
class PizzaControllerTest {

    @Mock
    PizzaRepository pizzaRepository;

    @Test
    void getAllPizzas() {

        PizzaController pizzaController = new PizzaController(pizzaRepository);
        assertThat(pizzaController.getAllPizzas().isEmpty());
    }

}