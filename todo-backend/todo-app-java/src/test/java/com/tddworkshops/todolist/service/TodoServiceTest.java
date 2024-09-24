package com.tddworkshops.todolist.service;

import com.tddworkshops.todolist.entity.Todo;
import com.tddworkshops.todolist.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class TodoServiceTest {

    @Mock
    private TodoRepository toDoRepository;

    @InjectMocks
    private TodoService toDoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetAllToDos_thenReturnListOfToDos() {
        // Arrange
        Todo task1 = new Todo(null, "Task 1", false);
        Todo task2 = new Todo(null, "Task 2", true);
        given(toDoRepository.findAll()).willReturn(Arrays.asList(task1, task2));

        // Act
        List<Todo> toDoList = toDoService.getAllToDos();

        // Assert
        assertThat(toDoList).hasSize(2);
        verify(toDoRepository).findAll();
    }

    @Test
    void whenGetToDoById_thenReturnToDo() {
        // Arrange
        Todo task = new Todo(null, "Task 1", false);
        given(toDoRepository.findById(1L)).willReturn(Optional.of(task));

        // Act
        Todo foundTask = toDoService.getToDoById(1L);

        // Assert
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getTask()).isEqualTo("Task 1");
    }
}
