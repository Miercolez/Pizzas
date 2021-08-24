package com.example.pizzas.controllers;

import com.example.pizzas.entities.Pizza;
import com.example.pizzas.repositories.PizzaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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
    List<Pizza> getSpecificPizza(@PathVariable("name") String name) {
        HashSet pizzas = new HashSet();
        pizzas.addAll(pizzaRepository.findPizzaByName(name));
        pizzas.addAll(pizzaRepository.findPizzaByIngredients(name));

        try {
            pizzas.addAll(pizzaRepository.findPizzaByPrice(Integer.parseInt(name)));
        } catch (NumberFormatException e) {
        }

        if (pizzas.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new ArrayList<>(pizzas);
    }


    @PostMapping("/pizzas")
    ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza) {

        pizzaRepository.save(pizza);

        return new ResponseEntity<>(pizza, HttpStatus.CREATED);
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

    @PatchMapping("/pizzas/{id}")
    Pizza partialUpdatePizza(@RequestBody Map<String, Object> changes, @PathVariable("id") Long id) {

        Pizza pizza = pizzaRepository.findById(id).get();

        changes.forEach((key, value) -> {
                    switch (key) {
                        case "name":
                            pizza.setName((String) value);
                            break;
                        case "price":
                            pizza.setPrice((int) value);
                            break;
                        case "ingredients":
                            pizza.setIngredients((List<String>) value);
                            break;
                    }
                }
        );

        return pizzaRepository.save(pizza);
    }

    @DeleteMapping("/pizzas/{id}")
    void deletePizza(@PathVariable("id") Long id) {
        pizzaRepository.deleteById(id);
    }

}
