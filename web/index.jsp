<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>General page</title>
</head>
<br>
<div align="center">

    <h2>Welcome!!!</h2><br/>

    <form method="post" action="auth">

        <table border="3">
            <tr>
                <th><label><input type="text" name="login"></label></th>
                <th>email</th>
            </tr>
            <tr>
                <th><label><input type="text" name="password"></label></th>
                <th>password</th>
            </tr>
            <tr>
                <th>
                    <button type="submit">OK</button>
                </th>
                <th></th>
            </tr>

        </table>
    </form>

</div>
</html>