<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><c:choose><c:when test="${not empty todo.id}">Update Entry</c:when><c:otherwise>Add New Entry</c:otherwise></c:choose></title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add.css" />
</head>
<body>
    <nav class="navbar">
        <div class="nav-container">
            <h1 class="logo">Interview Scheduler</h1>
        </div>
    </nav>

    <div class="container">
        <div class="form-section">
            <h2>
                <c:choose>
                    <c:when test="${not empty todo.id}">Update Entry</c:when>
                    <c:otherwise>Add New Student</c:otherwise>
                </c:choose>
            </h2>
            <form class="form-data" action="<c:choose><c:when test='${not empty todo.id}'>/update</c:when><c:otherwise>/add</c:otherwise></c:choose>" method="post">
                <input type="hidden" name="id" value="${todo.id}" />

                <div class="form-input">
                    <input type="text" name="name" id="name" value="${todo.name}" placeholder=" " required />
                    <label for="name">Enter a name</label>
                </div>

                <div class="form-input">
                    <input type="email" name="email" id="email" value="${todo.email}" placeholder=" " required />
                    <label for="email">Enter an email</label>
                </div>

                <div class="form-input">
                    <input type="number" name="mobile" id="mobile" value="${todo.mobile}" placeholder=" " required />
                    <label for="mobile">Enter a mobile number</label>
                </div>

                <div class="form-input">
                    <input type="text" name="reason" id="reason" value="${todo.reason}" placeholder=" " required />
                    <label for="reason">Enter a reason</label>
                </div>

                <div class="form-input btns">
                    <button class="add-btn" type="submit">
                        <c:choose>
                            <c:when test="${not empty todo.id}"><i class="fas fa-save"></i> Update</c:when>
                            <c:otherwise><i class="fas fa-plus"></i> Add</c:otherwise>
                        </c:choose>
                    </button>
                    <a href="/" class="cancel-btn">Go Back</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>