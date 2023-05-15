package com.amer.todomanagement.dto;

import com.amer.todomanagement.model.Todo;
import lombok.Data;

@Data
public class TaskDto {

    private long id;
    private String name;
    private String description;
}
