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
    private TodoRepository toDoRepository;

    @Test
    void whenFindAll_thenReturnAllToDos() {
        // Arrange
        Todo task1 = new Todo(null, "Task 1", false);
        Todo task2 = new Todo(null, "Task 2", true);
        toDoRepository.save(task1);
        toDoRepository.save(task2);

        // Act
        List<Todo> toDoList = toDoRepository.findAll();

        // Assert
        assertThat(toDoList).hasSize(2);
        assertThat(toDoList.get(0).getTask()).isEqualTo("Task 1");
        assertThat(toDoList.get(1).getTask()).isEqualTo("Task 2");
    }
}
