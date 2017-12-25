<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All orders</title>
</head>
<body>

<c:set var="orders" value="${orders}"/>
<c:set var="newOrder" value="${newOrder}"/>

<div align="center" style="width: 1024px; margin: 30px auto;">
    <table id="table_id" class="display">
        <thead>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>User</th>
            <th>Region</th>
            <th>Sum</th>
        </tr>
        </thead>
        <tbody id="tBody">
        <c:forEach items="${orders}" var="order">
            <tr>
                <tr>${order.id}</tr>
                <tr>${order.dateOrder}</tr>
                <tr>${order.user}</tr>
                <tr>${order.region}</tr>
                <tr>${order.sumOrder}</tr>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<div align="center" style="width: 1024px; margin: 0 auto;">
    <button id="menu" style="margin-top: 2px">Back to menu</button>
</div>

</body>
</html>


