package com.amer.todomanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String description;

    @JoinColumn(name="todo_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Todo todo;

    public Task(Long id, String name, String description, Todo todo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.setTodoItem(todo);
    }

    public Todo getTodoItem() {
        return todo;
    }

    public void setTodoItem(Todo todoItem) {
        this.todo = todoItem;
        this.todo.getTasks().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
