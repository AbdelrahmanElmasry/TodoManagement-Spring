package com.amer.todomanagement.controllers;


import com.amer.todomanagement.dto.TaskDto;
import com.amer.todomanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("todo/{todoId}/tasks")
    public List<TaskDto> getTasksByTodoId(@PathVariable(value = "todoId") Long todoId){
        return taskService.getTasksByTodoId(todoId);
    }

    @PostMapping("/todo/{todoId}/task")
    public ResponseEntity<TaskDto> save(@PathVariable(value="todoId") Long todoId, @RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(todoId, taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/todo/{todoId}/tasks/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(value="todoId") Long todoId, Long id) {
        TaskDto taskDto = taskService.getTaskById(id, todoId);

        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }


}
