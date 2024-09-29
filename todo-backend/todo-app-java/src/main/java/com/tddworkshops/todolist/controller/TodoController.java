package com.tddworkshops.todolist.controller;

import com.tddworkshops.todolist.entity.Todo;
import com.tddworkshops.todolist.service.TodoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Validated
public class TodoController {
    private final TodoService todoService;

    // Get all todo items
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    // Get a todo item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable @NotNull Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);

        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new todo item
    @PostMapping
    public Todo createTodo(@RequestBody @Valid Todo todo) {
        return todoService.createTodo(todo);
    }

    // Update an existing todo item
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable @NotNull Long id, @RequestBody @Valid Todo todoDetails) {
        Optional<Todo> updatedTodo = todoService.updateTodo(id, todoDetails);

        if (updatedTodo.isPresent()) {
            return ResponseEntity.ok(updatedTodo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a todo item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable @NotNull Long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}