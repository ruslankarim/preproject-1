<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020-03-02
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div align="center">
        <form action="login" method="post">
            <table border="1" cellpadding="5">
                <caption>
                </caption>
                <tr>
                    <th>Login: </th>
                    <td>
                        <input type="text" name="login" size="45"/>
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input type="text" name="password" size="45"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Login" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
