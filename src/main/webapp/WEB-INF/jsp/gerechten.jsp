<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Menu - JSP version</title>
</head>
<body>
<h1>Gerechten</h1>
<c:choose>
    <c:when test="${gerechten.size() != 0}">
<table>
    <thead>
    <th>Name</th>
    <th>Price</th>
    </thead>
    <tbody>
    <c:forEach var="gerecht" items="${gerechten}">
        <tr>
            <td>${gerecht.name}</td>AC
            <td>${gerecht.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:when>
<c:otherwise>
    <p>Er staan geen gerechten op het menu.</p>
</c:otherwise>
</c:choose>
<form action="/gerechten/"></form>
</body>
</html>