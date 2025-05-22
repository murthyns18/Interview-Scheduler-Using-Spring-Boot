package com.todo.main.service;

import java.util.List;

import com.todo.main.model.Todo;

public interface TodoService 
{
    List<Todo> getAllTodos();
    Todo saveTodo(Todo todo);
    void deleteTodo(int id);
    Todo getTodoById(int id);
}