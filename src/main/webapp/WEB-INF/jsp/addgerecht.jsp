<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="/./css/style.css">
    <title>Add gerecht</title>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Add gerecht"/>
    </jsp:include>
    <main>
        <c:if test="${errors!=null}">
            <div>
                <c:forEach items="${errors}" var="error">
                    <p>${error.field}: ${error.defaultMessage}</p>
                </c:forEach>
            </div>
        </c:if>

        <form method="post" action="/gerechten/add">
            <p>
                <label for="name">Name</label>
                <input id="name" type="text" name="name"/>
            </p>
            <p>
                <label for="price">Price</label>
                <input type="number" id="price" step="0.1" name="price"/>
            </p>

            <p>
                <label for="type">Type</label>
                <select name="type" id="type">
                    <c:forEach items="${types}" var="type">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select>
            </p>
            <p><input type="submit" value="Add"/></p>
            <p><input type="reset" value="Reset"/></p>
        </form>
    </main>
</div>
</body>
</html>