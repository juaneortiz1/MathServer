package com.example.MathServer1.controller;

import com.example.MathServer1.model.Greeting;
import com.example.MathServer1.model.MathSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    public Map<String, Object> linearSearch(@RequestParam(value = "list") String listStr,
                                            @RequestParam(value = "value") String value) {
        String[] items = listStr.split(",");
        int index = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].trim().equals(value)) {
                index = i;
                break;
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("operation", "linearSearch");
        response.put("inputlist", listStr);
        response.put("value", value);
        response.put("output", index);
        return response;
    }

    @GetMapping("/binarySearch")
    public Map<String, Object> binarySearch(@RequestParam(value = "list") String listStr,
                                            @RequestParam(value = "value") String value) {
        String[] items = listStr.split(",");
        Arrays.sort(items);
        int index = binarySearchRecursive(items, value, 0, items.length - 1);

        Map<String, Object> response = new HashMap<>();
        response.put("operation", "binarySearch");
        response.put("inputlist", listStr);
        response.put("value", value);
        response.put("output", index);
        return response;
    }

    private int binarySearchRecursive(String[] arr, String value, int start, int end) {
        if (start > end) return -1;
        int mid = start + (end - start) / 2;
        int compare = arr[mid].compareTo(value);
        if (compare == 0) return mid;
        else if (compare > 0) return binarySearchRecursive(arr, value, start, mid - 1);
        else return binarySearchRecursive(arr, value, mid + 1, end);
    }
}