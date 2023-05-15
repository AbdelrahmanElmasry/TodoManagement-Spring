package com.amer.todomanagement.controllers;

import com.amer.todomanagement.dto.TodoItemDto;
import com.amer.todomanagement.model.Todo;
import com.amer.todomanagement.repo.TaskRepo;
import com.amer.todomanagement.repo.TodoRepo;
import com.amer.todomanagement.service.TaskService;
import com.amer.todomanagement.service.TodoItemService;
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

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping
    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Todo> findOne(@PathVariable Long id) {
        return todoRepo.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TodoItemDto> save(@Valid @NotNull @RequestBody TodoItemDto todoItem) {
        return new ResponseEntity<>(todoItemService.createTodoItem(todoItem), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TodoItemDto> update(@Valid @NotNull @RequestBody TodoItemDto todoItem) {
        return new ResponseEntity<>(todoItemService.updateTodoItem(todoItem, todoItem.getId()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoItemService.deleteById(id);
    }
}

