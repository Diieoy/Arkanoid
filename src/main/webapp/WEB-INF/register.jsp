<%--
  Created by IntelliJ IDEA.
  User: Uzed
  Date: 03.05.2017
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
  <form action="<c:url value="/register"/>" method="post">
    <input type="text" name="username"/>
    <input type="text" name="email"/>
    <input type="password" name="password"/>
    <input type="submit" value="Зарегистрироваться"/>
  </form>
</body>
</html>
