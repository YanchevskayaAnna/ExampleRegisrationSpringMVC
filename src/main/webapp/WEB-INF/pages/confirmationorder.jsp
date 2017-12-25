<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Confirmation Page</title>
</head>
<body>
    user:${sessionScope.registeredUser}
    <br>
    region:${sessionScope.registeredOrder.region}
    <br>
    date:${sessionScope.registeredOrder.dateOrder}
    <br>
    sum order:${sessionScope.registeredOrder.sumOrder}
    <br>
    <hr>
     <sf:form action="confirmationorder" method="GET">
        <button name="confirmation" type="submit"> Confirmation
        </button>
    </sf:form>
</body>
</html>
