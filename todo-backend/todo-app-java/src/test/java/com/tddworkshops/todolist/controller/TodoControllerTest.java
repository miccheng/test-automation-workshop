package com.tddworkshops.todolist.controller;

import com.tddworkshops.todolist.entity.Todo;
import com.tddworkshops.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
@ExtendWith(MockitoExtension.class)
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TodoService todoService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void whenGetAllTodos_thenReturnTodoList() throws Exception {
        // Arrange
        Todo task1 = new Todo(null, "Task 1", false);
        Todo task2 = new Todo(null, "Task 2", true);
        List<Todo> todoList = Arrays.asList(task1, task2);
        given(todoService.getAllTodos()).willReturn(todoList);

        // Act and Assert
        mockMvc.perform(get("/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].task").value("Task 1"))
                .andExpect(jsonPath("$[1].task").value("Task 2"));
    }

    @Test
    void whenCreateTodo_thenReturnCreatedTodo() throws Exception {
        // Arrange
        Todo task = new Todo(null, "New Task", false);
        given(todoService.createTodo(task)).willReturn(task);

        // Act and Assert
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task").value("New Task"));
    }
}
