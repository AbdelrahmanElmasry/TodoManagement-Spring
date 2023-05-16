package com.amer.todomanagement.controllers;

import com.amer.todomanagement.dto.TodoItemDto;
import com.amer.todomanagement.model.Todo;
import com.amer.todomanagement.repo.TodoRepo;
import com.amer.todomanagement.service.TaskService;
import com.amer.todomanagement.service.TodoItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoItemController {
    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);
    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    @Operation(
            tags = {"Todo"},
            summary = "List all todos items"
    )
    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            tags = {"Todo"},
            summary = "Fetch specific todo item by id",
            parameters = { @Parameter(name = "todoId", description = "Id of the todo item to be fetched", example = "1")}
    )
    public Optional<Todo> findOne(@PathVariable Long id) {
        return todoRepo.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"Todo"},
            summary = "Create new todo item",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( description = "This is a simple todo body",
            content = @Content(schema = @Schema(implementation  = Todo.class))
            )

    )
    public ResponseEntity<TodoItemDto> save(@Valid @NotNull @RequestBody TodoItemDto todoItem) {
        return new ResponseEntity<>(todoItemService.createTodoItem(todoItem), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(
            tags = {"Todo"},
            summary = "Update specific todo ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( description = "This is a simple todo body",
                    content = @Content(schema = @Schema(implementation  = Todo.class))
            )
    )
    public ResponseEntity<TodoItemDto> update(@Valid @NotNull @RequestBody TodoItemDto todoItem) {
        return new ResponseEntity<>(todoItemService.updateTodoItem(todoItem, todoItem.getId()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            tags = {"Todo"},
            summary = "Delete specific todo by id ",
            parameters = { @Parameter(name = "todoId", description = "Id of the todo item to be deleted", example = "1")}
    )
    public void delete(@PathVariable Long id) {
        todoItemService.deleteById(id);
    }
}

