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
        <form method="post" action="/gerechten/update?beschrijving=${gerecht.name}" novalidate>
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="Beschrijving">Beschrijving</label><input type="text" id="Beschrijving" name="name"
                                                                    required value="${gerecht.name}"/></p>
            <p><label for="Type">Type</label><input type="text" id="Type" name="type"
                                                    required value="${gerecht.type}"/></p>
            <p><label for="Prijs">Prijs</label><input type="number" id="Prijs" name="price"
                                                      required value="${gerecht.price}"/></p>
            <p><input type="submit" id="cancel" name="update" value="Cancel"></p>
            <p><input type="submit" id="updateGerecht" name="update" value="Update"></p>
        </form>
    </main>
</div>
</body>
</html>