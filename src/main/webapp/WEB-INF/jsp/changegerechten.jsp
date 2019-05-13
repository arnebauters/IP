<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Change gerecht"/>
</jsp:include>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Change gerechten"/>
    </jsp:include>
    <main>
        <c:choose>
            <c:when test="${gerechten.size() != 0}">
                <table>
                    <c:forEach var="gerecht" items="${gerechten}">
                        <tr>
                            <td>${gerecht.name}</td>
                            <td>${gerecht.type}</td>
                            <td>${gerecht.price}</td>
                            <td><a href="/gerechten/update?name=${gerecht.name}">update</a></td>
                            <td><a href="/gerechten/delete?name=${gerecht.name}">delete</a></td>
                        </tr>
                    </c:forEach>
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
    </main>
</div>
</body>
</html>
