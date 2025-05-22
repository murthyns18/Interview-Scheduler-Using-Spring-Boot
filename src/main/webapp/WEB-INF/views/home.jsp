<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Interview Scheduler</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/todo.css" />
</head>
<body>
    <nav class="navbar">
        <div class="nav-container">
            <h1 class="logo">Interview Scheduler</h1>
            <div class="nav-buttons">
                <a href="/add" class="nav-btn adbtn"><i class="fas fa-plus"></i> Add New</a>
                <div class="nav-btn search-btn-wrapper">
                    <i class="fas fa-search"></i>
                    <input type="text" id="searchInput" placeholder="Search by name or date..." />
                </div>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="list-section">
            <h2>Interview List</h2>
            <div class="table-container">
                <table border="1" id="interviewTable">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Reason</th>
                            <th>Interview Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${todoList}" var="t">
                            <tr data-name="${t.name.toLowerCase()}" data-date="${not empty formattedDates[t.id] ? formattedDates[t.id] : ''}">
                                <td>${t.name}</td>
                                <td>${t.email}</td>
                                <td>${t.mobile}</td>
                                <td>${t.reason}</td>
                                <td class="interview-date">
                                    <c:choose>
                                        <c:when test="${not empty formattedDates[t.id]}">
                                            ${formattedDates[t.id]}
                                        </c:when>
                                        <c:otherwise>
                                            <span class="not-scheduled">Not Scheduled</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="action-links">
                                    <a href="/updateDate/${t.id}" class="update-link"><i class="far fa-calendar-alt"></i> Update Date</a>
                                    <a href="/delete/${t.id}" class="delete-link"> 
                                        <i class="far fa-trash-alt"></i> Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/todo.js"></script>
</body>
</html>
