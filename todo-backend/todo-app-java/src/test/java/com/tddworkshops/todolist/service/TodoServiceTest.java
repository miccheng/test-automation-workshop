package com.tddworkshops.todolist.service;

import com.tddworkshops.todolist.entity.Todo;
import com.tddworkshops.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;

    @Test
    void whenGetAllTodos_thenReturnListOfTodos() {
        // Arrange
        Todo task1 = new Todo(null, "Task 1", false);
        Todo task2 = new Todo(null, "Task 2", true);
        given(todoRepository.findAll()).willReturn(Arrays.asList(task1, task2));

        // Act
        List<Todo> todoList = todoService.getAllTodos();

        // Assert
        assertThat(todoList).hasSize(2);
        verify(todoRepository).findAll();
    }

    @Test
    void whenGetTodoById_thenReturnTodo() {
        // Arrange
        Todo task = new Todo(1L, "Task 1", false);
        given(todoRepository.findById(1L)).willReturn(Optional.of(task));

        // Act
        Optional<Todo> foundTask = todoService.getTodoById(1L);

        // Assert
        assertThat(foundTask.isPresent()).isTrue();
        assertThat(foundTask.get().getTask()).isEqualTo("Task 1");
    }
}
