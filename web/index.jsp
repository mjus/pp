<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>

<html>
<head>
  <title>$Title$</title>
</head>
<br>

<h2>All users</h2><br/>

<table border="3">
  <tr>
    <th>ID</th>
    <th>name</th>
    <th>soname</th>
    <th>email</th>
  </tr>
  <%
    try {
      List<User> users = new UserService().getAllUsers();
      for (User user : users) {
  %>
  <tr>
    <td><%= user.getId() %>
    </td>
    <td><%= user.getName() %>
    </td>
    <td><%= user.getSoName() %>
    </td>
    <td><%= user.getEmail() %>
    </td>
  </tr>
  <%
      }
    }catch (Exception e){
      System.out.println(e);
    }
  %>
</table>

<h3>Add new user</h3><br/>

<form method="post" action="">

  <label><input type="text" name="name"></label>name<br>

  <label><input type="text" name="soName"></label>soname<br>

  <label><input type="text" name="email"></label>email<br>


  <input type="submit" value="Ok" name="Ok"><br>
</form>

<h3>Remove user by ID</h3><br/>

<form method="post" action="/delete">

  <label><input type="text" name="id"></label>id<br>

  <input type="submit" value="Ok" name="Ok"><br>
</form>

<h3>Update user by ID</h3><br/>

<form method="post" action="/update">

  <label><input type="text" name="id"></label>id<br>
  <label><input type="text" name="name"></label>name<br>
  <label><input type="text" name="soName"></label>soname<br>
  <label><input type="text" name="email"></label>email<br>

  <input type="submit" value="Ok" name="Ok"><br>
</form>


</body>
</html>