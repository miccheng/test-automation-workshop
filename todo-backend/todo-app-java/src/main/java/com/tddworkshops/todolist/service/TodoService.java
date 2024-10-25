package com.tddworkshops.todolist.service;

import com.tddworkshops.todolist.entity.Todo;
import com.tddworkshops.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> updateTodo(Long id, Todo todoDetails) {
        if (getTodoById(id).isPresent()) {
            todoDetails.setId(id);
            return Optional.of(todoRepository.save(todoDetails));
        } else {
            return Optional.empty();
        }
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional
    public void clearCompletedTodos() {
        todoRepository.deleteByCompleted(true);
    }
}
