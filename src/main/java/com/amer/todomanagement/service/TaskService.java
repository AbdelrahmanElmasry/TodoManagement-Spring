package com.amer.todomanagement.service;

import com.amer.todomanagement.dto.TaskDto;
import com.amer.todomanagement.model.Task;

import java.util.List;

public interface TaskService {
    TaskDto createTask(Long todoItemId, TaskDto taskDto);

    List<TaskDto> getTasksByTodoId(Long id);

    TaskDto getTaskById(Long taskId, Long todoId);

    Task mapToEntity(TaskDto taskDto);

    TaskDto mapToDto(Task task);

}
