package com.example.pizzas.controllers;

import com.example.pizzas.entities.Pizza;
import com.example.pizzas.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{name}")
    List<Pizza> getPizzaById(@PathVariable("name") String name) {
        HashSet pizzas = new HashSet();
        pizzas.addAll(pizzaRepository.findPizzaByName(name));
        pizzas.addAll(pizzaRepository.findPizzaByIngredients(name));

        try {
            pizzas.addAll(pizzaRepository.findPizzaByPrice(Integer.parseInt(name)));
        }catch (NumberFormatException e){
        }

        return new ArrayList<>(pizzas);
    }

    @PostMapping("/pizzas")
    Pizza addPizza(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @PutMapping("/pizzas/{id}")
    Optional<Pizza> updatePizza(@RequestBody Pizza newPizza, @PathVariable("id") Long id) {
        return pizzaRepository.findById(id)
                .map(pizza -> {
                    pizza.setName(newPizza.getName());
                    pizza.setIngredients(newPizza.getIngredients());
                    pizza.setPrice(newPizza.getPrice());
                    return pizzaRepository.save(pizza);
                });
    }

    @DeleteMapping("/pizzas/{id}")
    void deletePizza(@PathVariable("id") Long id){
        pizzaRepository.deleteById(id);
    }

}
