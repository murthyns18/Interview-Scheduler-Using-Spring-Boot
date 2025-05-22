package com.todo.main.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.main.model.Todo;
import com.todo.main.service.TodoService;

@Controller
public class TodoController 
{	
    @Autowired
    private TodoService todoService;

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    @GetMapping("/")
    public String index(Model model) 
    {
        List<Todo> todos = todoService.getAllTodos();

        Map<Integer, String> formattedDates = todos.stream()
                .filter(t -> t.getInterviewDate() != null)
                .collect(Collectors.toMap(
                        Todo::getId,
                        t -> t.getInterviewDate().format(DISPLAY_FORMATTER)
                ));

        model.addAttribute("todoList", todos);
        model.addAttribute("formattedDates", formattedDates);

        return "home";
    }

    @GetMapping("/add")
    public String addForm(Model model) 
    {
        model.addAttribute("todo", new Todo());
        return "add";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) 
    {
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable int id, Model model)
    {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "add"; 
    }

    @PostMapping("/update")
    public String updateTodo(@ModelAttribute Todo todo) 
    {
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/updateDate/{id}")
    public String updateDatePage(@PathVariable int id, Model model) 
    {
        Todo todo = todoService.getTodoById(id);

        String formattedInterviewDate = "";
        if (todo.getInterviewDate() != null) 
        {
            formattedInterviewDate = todo.getInterviewDate().toString().replace(" ", "T");
        }

        model.addAttribute("todo", todo);
        model.addAttribute("formattedInterviewDate", formattedInterviewDate);
        return "updateDate";
    }

    @PostMapping("/saveDate")
    public String saveInterviewDate(@RequestParam int id,
                                    @RequestParam("interviewDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime interviewDate) 
    {

        Todo todo = todoService.getTodoById(id);
        todo.setInterviewDate(interviewDate);
        todoService.saveTodo(todo);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable int id) 
    {
        todoService.deleteTodo(id);
        return "redirect:/";
    }
    
    @PostMapping("/todos")
    public ResponseEntity<Todo> addOrUpdateTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoService.saveTodo(todo);
        return ResponseEntity.ok(savedTodo);
    }

    @PostMapping("/todos/{id}/date")
    public ResponseEntity<Todo> updateInterviewDate(
            @PathVariable int id,
            @RequestBody Map<String, String> request) {
        
        LocalDateTime interviewDate = LocalDateTime.parse(request.get("interviewDate"));
        Todo todo = todoService.getTodoById(id);
        todo.setInterviewDate(interviewDate);
        Todo savedTodo = todoService.saveTodo(todo);
        
        return ResponseEntity.ok(savedTodo);
    }
    
}