package com.example.pizzas.controllers;

import com.example.pizzas.entities.Pizza;
import com.example.pizzas.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    Optional<Pizza> getPizzaById(@PathVariable("id") Long id){
        return pizzaRepository.findById(id);
    }

    @PutMapping("/pizzas/{id}")
    Optional<Pizza> updatePizza(@RequestBody Pizza newPizza, @PathVariable("id") Long id){
        return pizzaRepository.findById(id);
    }

}
