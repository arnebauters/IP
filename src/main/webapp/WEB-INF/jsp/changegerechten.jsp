<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Menu - JSP version</title>
</head>
<body>
<h1>Change gerechten</h1>
<c:choose>
    <c:when test="${gerechten.size() != 0}">
        <table>
            <tbody>
            <c:forEach var="gerecht" items="${gerechten}">
                <tr>
                    <td>${gerecht.name}</td>
                    <td><a href="#">update</a></td>
                    <td><a href="/gerechten/delete?beschrijving=${gerecht.type} ${gerecht.name}">delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Er staan geen gerechten op het menu.</p>
    </c:otherwise>
</c:choose>
<form action="/gerechten/add" method="get">
    <p>
    <input type="submit" value="Add Gerecht"/>
    </p>
</form>
</body>
</html>