package com.amer.todomanagement.service;

import com.amer.todomanagement.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(Long todoItemId, TaskDto taskDto);

    List<TaskDto> getTasksByTodoId(Long id);

    TaskDto getTaskById(Long taskId, Long todoId);
}
