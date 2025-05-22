package com.todo.main.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String mobile;
    private String reason;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime interviewDate;

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getEmail() 
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getReason()
    {
        return reason;
    }
    
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public LocalDateTime getInterviewDate()
    {
        return interviewDate;
    }
    
    public void setInterviewDate(LocalDateTime localDateTime)
    {
        this.interviewDate = localDateTime;
    }
}
