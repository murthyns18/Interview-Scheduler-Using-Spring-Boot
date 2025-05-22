package com.todo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToDoMain 
{
    public static void main(String[] args) 
    {
        SpringApplication.run(ToDoMain.class, args);
    }
}
