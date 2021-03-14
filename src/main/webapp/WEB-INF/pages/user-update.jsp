<%--
  Created by IntelliJ IDEA.
  User: Евгений Кашицын
  Date: 11.03.2021
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Update</title>
</head>
<body>
<form action="#" th:action="@{/user-update}" th:object="${user}" method="post">
    <label for="id">ID</label>
    <input readonly type="number" th:field="*{id}" id="id" placeholder="ID">

    <br/>
    <label for="userName">User name</label>
    <input type="text" th:field="*{userName}" id="userName" placeholder="User Name">

    <br/>
    <label for="firstName">First name</label>
    <input type="text" th:field="*{firstName}" id="firstName" placeholder="First Name">

    <br/>
    <label for="lastName">Last name</label>
    <input type="text" th:field="*{lastName}" id="lastName" placeholder="Last Name">

    <br/>
    <input type="submit" value="Update User">
</form>
</body>
</html>
