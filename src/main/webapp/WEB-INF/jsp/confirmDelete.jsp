<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Delete gerecht"/>
</jsp:include>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Delete gerecht"/>
    </jsp:include>
    <main>


        <table>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Type</th>
            </tr>
            <tr>
                <td><c:out value="${ gerecht.name }"/></td>
                <td><c:out value="${ gerecht.price }"/></td>
                <td><c:out value=" ${ gerecht.type } "/></td>
            </tr>
        </table>

        <ul>
            <li>
                <a href="/gerechten">NO</a>
            </li>
            <li>
                <a href="/gerecht/confirmDelete?name=${ gerecht.name }">YES</a>
            </li>
        </ul>
    </main>
</body>
</html>
