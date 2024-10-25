package com.tddworkshops.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClearCompletedResponseModel {
    private String message;
}
