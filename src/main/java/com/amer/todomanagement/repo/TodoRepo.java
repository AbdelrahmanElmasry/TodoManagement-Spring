package com.amer.todomanagement.repo;

import com.amer.todomanagement.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
}
