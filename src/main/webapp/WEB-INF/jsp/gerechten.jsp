<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="/./css/style.css">
    <title>Gerechten</title>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Home"/>
    </jsp:include>
    <c:choose>
    <c:when test="${gerechten.size() != 0}">
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <c:forEach var="gerecht" items="${gerechten}">
                <tr>
                    <td>${gerecht.name}</td>
                    <td>${gerecht.price}</td>
                </tr>
            </c:forEach>
            <caption>Gerechten overview</caption>
        </table>
        </c:when>
        <c:otherwise>
            <p>Er staan geen gerechten op het menu.</p>
        </c:otherwise>
        </c:choose>
    </main>
</div>
</body>
</html>