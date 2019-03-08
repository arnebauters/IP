<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="/./css/style.css">
    <title>Delete</title>
</head>
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