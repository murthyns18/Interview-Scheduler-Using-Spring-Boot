package com.todo.main.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.main.model.Todo;


public interface TodoRepository extends JpaRepository<Todo, Integer> 
{
	 List<Todo> findByInterviewDateBetween(LocalDateTime start, LocalDateTime end);
}
