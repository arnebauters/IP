<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="/./css/style.css">
    <title>Update gerecht</title>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Home"/>
    </jsp:include>
    <main>
        <c:if test="${errors!=null}">
            <div>
                <c:forEach items="${errors}" var="error">
                    <p>${error.field}: ${error.defaultMessage}</p>
                </c:forEach>
            </div>
        </c:if>
        <form method="post" action="/gerechten/update" novalidate>
            <!-- novalidate in order to be able to run tests correctly -->
            <input type="hidden" name="id" value="${gerecht.id}">
            <p>
                <label for="name">Name: </label>
                <input type="text" id="name" name="name" required value="${gerecht.name}"/>
            </p>
            <p>
                <label for="type">Type: </label>
                <select name="type" id="type" selected= ${gerecht.type}>
                <c:forEach items="${types}" var="type">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
            </p>
            <p>
                <label for="price">Prijs: </label>
                <input type="number" id="price" name="price" required value="${gerecht.price}"/>
            </p>
            <p>
                <a href="/gerechten/change"> Cancel</a>
            </p>
            <p>
                <input type="submit" value="Update">
            </p>
        </form>
    </main>
</div>
</body>
</html>