package com.amer.todomanagement.controllers;


import com.amer.todomanagement.dto.TaskDto;
import com.amer.todomanagement.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(
            tags = {"Task"},
            summary = "List all todo tasks by todoId",
            parameters = { @Parameter(name = "todoId", description = "Id of the todo item to fetch all its tasks", example = "1")}
    )
    @GetMapping("todo/{todoId}/tasks")
    public List<TaskDto> getTasksByTodoId(@PathVariable(value = "todoId") Long todoId){
        return taskService.getTasksByTodoId(todoId);
    }

    @Operation(
            tags = {"Task"},
            summary = "Add task to a specific todo",
            parameters = { @Parameter(name = "todoId", description = "Id of the todo item to add a new task", example = "1")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( description = "This is a simple task body",
            content = @Content(schema = @Schema(implementation  = TaskDto.class))
            )
    )
    @PostMapping("/todo/{todoId}/task")
    public ResponseEntity<TaskDto> save(@PathVariable(value="todoId") Long todoId, @RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(todoId, taskDto), HttpStatus.CREATED);
    }

    @Operation(
            tags = {"Task"},
            summary = "fetch specific task on a todo",
            parameters = { @Parameter(name = "todoId", description = "Id of the todo item", example = "1"),
                           @Parameter(name = "id", description = "Id of the todo task item", example = "1")}

    )
    @GetMapping("/todo/{todoId}/task/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(value="todoId") Long todoId, Long id) {
        TaskDto taskDto = taskService.getTaskById(id, todoId);

        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }


}
