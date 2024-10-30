package com.tddworkshops.todolist.repository;

import com.tddworkshops.todolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@DataJpaTest
public class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    void whenFindAll_thenReturnAllTodos() {
        // Arrange
        Todo task1 = new Todo(null, "Task 1", false);
        Todo task2 = new Todo(null, "Task 2", true);
        todoRepository.save(task1);
        todoRepository.save(task2);

        // Act
        List<Todo> todoList = todoRepository.findAll();

        // Assert
        assertThat(todoList).hasSize(2);
        assertThat(todoList.get(0).getTask()).isEqualTo("Task 1");
        assertThat(todoList.get(1).getTask()).isEqualTo("Task 2");
    }

    @Test
    void whenDeletedByCompleted_thenReturnAllUnclearedTodos() {
        // Arrange
        Todo task1 = new Todo(null, "Incomplete Task", false);
        Todo task2 = new Todo(null, "Completed Task", true);
        todoRepository.save(task1);
        todoRepository.save(task2);

        // Act - Remove all completed tasks
        todoRepository.deleteByCompleted(true);
        
        // Assert
        List<Todo> todoList = todoRepository.findAll();
        assertThat(todoList).hasSize(1);
        assertThat(todoList.get(0).getTask()).isEqualTo("Incomplete Task");

        // Act - Remove all incomplete tasks
        todoRepository.deleteByCompleted(false);

        // Assert
        todoList = todoRepository.findAll();
        assertThat(todoList).hasSize(0);
    }
}
