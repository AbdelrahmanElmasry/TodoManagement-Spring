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

    @Override
    public TodoItemDto createTodoItem(TodoItemDto todoItemDto) {

        Todo todoItem = mapToEntity(todoItemDto);
        Todo newTodo = todoItemRepo.save(todoItem);
        TodoItemDto todoItemResponse = mapToDto(newTodo);

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
        List<Task> tasks = todo.getTasks();
        List<TaskDto> tasksDto = new ArrayList<>();
        for(Task task: tasks) {
            tasksDto.add(taskService.mapToDto(task));
        }
        return todoItemDto;
    }

    private Todo mapToEntity(TodoItemDto todoItemDto) {
        Todo todo = new Todo();

        todo.setName(todoItemDto.getName());
        todo.setDescription(todoItemDto.getDescription());
        List<TaskDto> tasksDto = todoItemDto.getTasks();
        for(TaskDto taskDto: tasksDto) {
            Task task = taskService.mapToEntity(taskDto);
            task.setTodoItem(todo);
            todo.getTasks().add(task);
        }

        return todo;
    }
}
