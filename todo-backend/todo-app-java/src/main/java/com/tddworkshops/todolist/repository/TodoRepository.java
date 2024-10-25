package com.tddworkshops.todolist.repository;

import com.tddworkshops.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    void deleteByCompleted(boolean completed);
}
