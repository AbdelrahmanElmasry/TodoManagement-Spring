package com.amer.todomanagement.service.impl;

import com.amer.todomanagement.dto.TaskDto;
import com.amer.todomanagement.exceptions.NotFoundException;
import com.amer.todomanagement.model.Task;
import com.amer.todomanagement.model.Todo;
import com.amer.todomanagement.repo.TaskRepo;
import com.amer.todomanagement.repo.TodoRepo;
import com.amer.todomanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TodoRepo todoRepo;


    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public TaskDto createTask(Long todoId, TaskDto taskDto) {
        Task task = mapToEntity(taskDto);

        Todo todoItem = todoRepo.findById(todoId).orElseThrow(() -> new NotFoundException("Resource not found"));

        task.setTodoItem(todoItem);
        Task newTask = taskRepo.save(task);
        return mapToDto(newTask);
    }

    @Override
    public List<TaskDto> getTasksByTodoId(Long id) {
        List<Task> tasks = taskRepo.findByTodoId(id);

        return tasks.stream().map(task -> mapToDto(task)).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long taskId, Long todoId) {
        Todo todo = todoRepo.findById(todoId).orElseThrow(() -> new NotFoundException("Todo with this id not found!"));

        Task task = taskRepo.findById(taskId).orElseThrow(() -> new NotFoundException("Task with this id not found!"));

        if(task.getTodoItem().getId() != todo.getId()) {
            throw new NotFoundException("This task doesn't belong to a todo");
        }

        return mapToDto(task);
    }

    @Override
     public TaskDto mapToDto(Task task){
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        return taskDto;
    }

    @Override
    public Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();

        if (taskDto.getId() > 0) {
            task.setId(taskDto.getId());
        }
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());

        return task;
    }
}
