package com.amer.todomanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class TodoItemDto {

    private long id;
    private String name;
    private String description;
    private List<TaskDto> tasks;
}
