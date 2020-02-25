<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020-02-24
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Users Application</title>
  </head>
  <body>
    <center>
      <h1>Users Management</h1>
      <h2>
        <a href="new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Users</a>
      </h2>
    </center>
    <div align="center">
      <table border="1" cellpadding="5">
        <caption><h2>List Of Users</h2></caption>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Address</th>
          <c:forEach var="user" items="${users}">
            <tr>
              <td><c:out value="${user.id}" /></td>
              <td><c:out value="${user.name}" /></td>
              <td><c:out value="${user.address}" /></td>
              <td>
                <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
              </td>
            </tr>
        </c:forEach>
        </tr>
      </table>
    </div>
  </body>
</html>
