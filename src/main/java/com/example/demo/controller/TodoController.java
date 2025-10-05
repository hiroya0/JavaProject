package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Todo;
import java.util.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private List<Todo> todos = new ArrayList<>();

    @GetMapping
    public List<Todo> getTodos() {
        return todos;
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        todos.add(todo);
        return todo;
    }
}
