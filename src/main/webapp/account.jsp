<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<section>
    <h2>Account info</h2>

    <h3>Все Аккаунты</h3>
    <c:forEach var="account" items="${requestScope.accounts}">
        <ul>
            <li>ID: <c:out value="${account.id}"/></li>

            <li>Имя: <c:out value="${account.name}"/></li>

            <li>Автор: <c:out value="${account.accountStatus}"/></li>
        </ul>
        <hr />
    </c:forEach>
</section>
</body>
</html>