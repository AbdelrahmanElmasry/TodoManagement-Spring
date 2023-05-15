package com.amer.todomanagement.service.impl;

import com.amer.todomanagement.dto.TaskDto;
import com.amer.todomanagement.dto.TodoItemDto;
import com.amer.todomanagement.exceptions.NotFoundException;
import com.amer.todomanagement.model.Task;
import com.amer.todomanagement.model.Todo;
import com.amer.todomanagement.repo.TaskRepo;
import com.amer.todomanagement.repo.TodoRepo;
import com.amer.todomanagement.service.TaskService;
import com.amer.todomanagement.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    @Autowired
    private TodoRepo todoItemRepo;

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private TaskService taskService;

    @Autowired
    public TodoItemServiceImpl(TodoRepo todoItemRepo) {
        this.todoItemRepo = todoItemRepo;
    }

    @Override
    public TodoItemDto createTodoItem(TodoItemDto todoItemDto) {
        Todo todoItem = new Todo();
        todoItem.setName(todoItemDto.getName());
        todoItem.setDescription(todoItemDto.getDescription());
        List<TaskDto> todoTasks = todoItemDto.getTasks();

        Todo newTodo = todoItemRepo.save(todoItem);
        if (todoTasks.size() > 0) {
            for (TaskDto task : todoTasks){
               taskService.createTask(newTodo.getId(), task);
            }
        }

        TodoItemDto todoItemResponse = new TodoItemDto();
        todoItemResponse.setId(newTodo.getId());
        todoItemResponse.setName(newTodo.getName());
        todoItemResponse.setDescription(newTodo.getDescription());
        todoItemResponse.setTasks(todoTasks);

        return todoItemResponse;
    }

    @Override
    public TodoItemDto updateTodoItem(TodoItemDto todoItemDto, Long id) {
        Todo todoItem = todoItemRepo.findById(id).orElseThrow(() -> new NotFoundException("Todo with this id not found!"));

        todoItem.setName(todoItemDto.getName());
        todoItem.setDescription(todoItemDto.getDescription());

        Todo updatedTodo = todoItemRepo.save(todoItem);

        return mapToDto(updatedTodo);
    }

    private TodoItemDto mapToDto(Todo todo){
        TodoItemDto todoItemDto = new TodoItemDto();

        todoItemDto.setId(todo.getId());
        todoItemDto.setName(todo.getName());
        todoItemDto.setDescription(todo.getDescription());
        return todoItemDto;
    }

    public void deleteById(Long id) {
        taskRepo.deleteById(id);

        List<TaskDto> tasksDto = taskService.getTasksByTodoId(id);

        for (TaskDto task : tasksDto) {
            taskRepo.deleteById(task.getId());
        }
    }
}
