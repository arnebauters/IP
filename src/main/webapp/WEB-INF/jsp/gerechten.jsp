<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Gerechten"/>
</jsp:include>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Home"/>
    </jsp:include>
    <main>
    <c:choose>
    <c:when test="${gerechten.size() != 0}">
        <table>
            <tr>
                <th>Name</th>
                <th>Type</th>
                <th>Price</th>
            </tr>
            <c:forEach var="gerecht" items="${gerechten}">
                <tr>
                    <td>${gerecht.name}</td>
                    <td>${gerecht.type}</td>
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
