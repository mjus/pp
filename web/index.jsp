<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<div align="center">
    <body>
    <h3>All users</h3>
    <table border="3" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>surname</th>
            <th>email</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tr>
            <form action="" method="POST">
                <th><input type="hidden" name="action" value="add" ></th>
                <th><input type="text" name="name"></th>
                <th><input type="text" name="surname"></th>
                <th><input type="text" name="email"></th>
                <th>
                    <input type="submit" value="add"></type>
                </th>
                <th></th>
            </form>
        </tr>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td><form action="/update" method="POST">
                    <a href="/?action=update&id=${user.id}">edit</a></form></td>
                <td><form action="/delete" method="POST">
                    <a href="/?action=delete&id=${user.id}">delete</a></form></td>
            </tr>
        </c:forEach>
    </table>
    </body>
</div>
</html>