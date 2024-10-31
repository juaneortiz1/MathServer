package com.example.MathServer1.controller;

import com.example.MathServer1.model.Greeting;
import com.example.MathServer1.model.MathSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/linearSearch")
    /*public MathSearch linearSearch(@RequestParam(value = "numlist") Double[] lista) {
        return new MathSearch();
    }*/
    public Greeting greeting1(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/binarySearch")
    /*public MathSearch binarySearch(@RequestParam(value = "numlist") Double[] lista) {
        return new MathSearch();
    }*/
    public Greeting greeting2(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}