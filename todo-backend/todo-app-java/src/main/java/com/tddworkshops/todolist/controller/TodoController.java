package com.tddworkshops.todolist.controller;

import com.tddworkshops.todolist.entity.Todo;
import com.tddworkshops.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService toDoService;

    // Get all to-do items
    @GetMapping
    public List<Todo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    // Get a to-do item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getToDoById(@PathVariable Long id) {
        Todo toDo = toDoService.getToDoById(id);
        if (toDo != null) {
            return ResponseEntity.ok(toDo);
        }
        return ResponseEntity.notFound().build();
    }

    // Create a new to-do item
    @PostMapping
    public Todo createToDo(@RequestBody Todo toDo) {
        return toDoService.createToDo(toDo);
    }

    // Update an existing to-do item
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateToDo(@PathVariable Long id, @RequestBody Todo toDoDetails) {
        Todo updatedToDo = toDoService.updateToDo(id, toDoDetails);
        if (updatedToDo != null) {
            return ResponseEntity.ok(updatedToDo);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a to-do item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoById(@PathVariable Long id) {
        toDoService.deleteToDoById(id);
        return ResponseEntity.noContent().build();
    }
}