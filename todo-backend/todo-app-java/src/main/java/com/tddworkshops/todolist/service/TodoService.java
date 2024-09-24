package com.tddworkshops.todolist.service;

import com.tddworkshops.todolist.entity.Todo;
import com.tddworkshops.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository toDoRepository;

    public List<Todo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public Todo getToDoById(Long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    public Todo createToDo(Todo toDo) {
        return toDoRepository.save(toDo);
    }

    public Todo updateToDo(Long id, Todo toDoDetails) {
        Todo toDo = getToDoById(id);
        if (toDo != null) {
            toDo.setTask(toDoDetails.getTask());
            toDo.setCompleted(toDoDetails.isCompleted());
            return toDoRepository.save(toDo);
        }
        return null;
    }

    public void deleteToDoById(Long id) {
        toDoRepository.deleteById(id);
    }
}
