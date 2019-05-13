<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Delete"/>
</jsp:include>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Delete gerecht"/>
    </jsp:include>
    <main>
        <form action="/gerechten/delete?beschrijving=${name}" method="post">
            <p>Bent u zeker dat u volgend gerecht: ${name} wilt verwijderen?</p>
            <p><input type="submit" name="confirm" value="Ja"/></p>
            <p><input type="submit" name="confirm" value="Nee"></p>
        </form>
    </main>
</div>
</body>
</html>
