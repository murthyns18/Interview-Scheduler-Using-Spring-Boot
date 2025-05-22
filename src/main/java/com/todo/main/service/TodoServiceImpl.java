package com.todo.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.main.model.Todo;
import com.todo.main.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService 
{

    @Autowired
    private TodoRepository todoRepo;

    public List<Todo> getAllTodos() 
    {
        return todoRepo.findAll();
    }

    public Todo saveTodo(Todo todo) 
    {
        return todoRepo.save(todo);
    }

    public void deleteTodo(int id) 
    {
        todoRepo.deleteById(id);
    }

    public Todo getTodoById(int id) 
    {
        return todoRepo.findById(id).orElse(null);
    }
}