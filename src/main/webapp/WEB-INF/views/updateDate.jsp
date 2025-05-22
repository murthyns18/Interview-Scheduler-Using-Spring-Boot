<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Update Interview Date</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateDate.css" />
</head>
<body>
    <nav class="navbar">
        <div class="nav-container">
            <h1 class="logo">Interview Scheduler</h1>
        </div>
    </nav>

    <div class="container">
        <div class="update-date-section">
            <h2>Update Interview Date for: ${todo.name}</h2>
            
            <form action="/saveDate" method="post">
                <input type="hidden" name="id" value="${todo.id}" />
                
                <div class="form-input">
                    <label>Set Interview Date and Time</label><br>
                    <input type="datetime-local" name="interviewDate" value="${formattedInterviewDate}" required />
                </div>
                
                <div class="form-input btns">
                    <button type="submit" class="save-btn"><i class="fas fa-save"></i> Save Date</button>
                    <a href="/" class="save-btn">Go Back</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>