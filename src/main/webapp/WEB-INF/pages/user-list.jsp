<%--
  Created by IntelliJ IDEA.
  User: Евгений Кашицын
  Date: 11.03.2021
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<div th:switch="${users}">
    <h2 th:case="null">No users found!</h2>
    <div th:case="*">
        <h2>Users</h2>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>User name</th>
                <th>First name</th>
                <th>Last name</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="user : ${users}">
                <td th:text="${user.id}"></td>
                <td th:text="${user.userName}"></td>
                <td th:text="${user.firstName}"></td>
                <td th:text="${user.lastName}"></td>
                <td><a th:href="@{user-update/{id}(id=${user.id})}">Edit</a></td>
                <td><a th:href="@{user-delete/{id}(id=${user.id})}">Delete</a></td>
            </tr>
            </tbody>
        </table>
    </div>
    <p><a href="/user-create">Create user</a></p>
</div>
</body>
</html>
