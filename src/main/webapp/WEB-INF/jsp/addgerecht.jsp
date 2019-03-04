<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Add gerecht</title>
</head>
<body>
<h1>Add gerecht</h1>
<c:if test="${errors!=null}">
    <div>
        <c:forEach items="${errors}" var="error">
            <p>${error.field}: ${error.defaultMessage}</p>
        </c:forEach>
    </div>
</c:if>

<form method="post" action="/gerechten/add">
    <p>
        <label>Name</label>
        <input type="text" name="name" />
    </p>
    <p>
        <label>Price</label>
        <input type="number" step="0.1" name="price" />
    </p>

    <p>
        <label>Type</label>
        <select name="type">
            <c:forEach items="${types}" var="type">
                <option value="${type}">${type}</option>
            </c:forEach>
        </select>
    </p>
    <p>
        <input type="submit" value="Add" />
        <input type="reset" value="Reset" />
    </p>
</form>
</body>
</html>