package com.tddworkshops.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tddworkshops.todolist.entity.Todo;

@RepositoryRestResource
@CrossOrigin
public interface TodoRepository extends CrudRepository<Todo, Long> {
}