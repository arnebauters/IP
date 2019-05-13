<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body>
<ul>
    <li><a href="/gerechten/">View Gerechten</a></li>
    <li><a href="/gerechten/change">Change a gerecht</a></li>
    <li><a href="/gerechten/add">Add a Gerechten</a></li>
</ul>
</body>
</html>
