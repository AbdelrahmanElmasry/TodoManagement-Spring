package com.amer.todomanagement.service;

import com.amer.todomanagement.dto.TodoItemDto;

public interface TodoItemService {
    TodoItemDto createTodoItem(TodoItemDto todoItemDto);
    TodoItemDto updateTodoItem(TodoItemDto todoItemDto, Long id);

    void deleteById(Long id);

}
