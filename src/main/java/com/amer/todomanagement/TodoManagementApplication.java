package com.amer.todomanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Todo-Tasks API", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:8080")},
		tags = {@Tag(name = "Todo", description = "This is the todo resource where you can manage your own todos."),
				@Tag(name = "Task", description = "This is the task resource where you can manage your own todo tasks.")}
)
@SpringBootApplication
public class TodoManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoManagementApplication.class, args);
	}

}
