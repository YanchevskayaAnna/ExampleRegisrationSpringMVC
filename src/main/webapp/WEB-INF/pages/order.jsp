<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Order jsp</title>
    <style>
        .error{
            color:#ff0000;
        }
    </style>
</head>
<body>
<%--it is a link to RegistrationController method doRegistration()--%>
<c:set var="urladdr" value="makeorder"/>
<%--Spring form--%>
<%-- modelAttribute values is important --%>
<sf:form modelAttribute="order" action="${urladdr}">
    <%--spring label--%>
    <label for="region">
        <s:message code="property.enterRegion"/>
    </label>
    <sf:input path="region"/>
    <sf:errors path="region" cssClass="error"/>
    <br>
    <label for="dateOrder">
        <s:message code="property.enterDateOrder"/>
    </label>
    <sf:input path="dateOrder"/>
    <sf:errors path="dateOrder" cssClass="error"/>
    <br>
    <label for="sumOrder">
        <s:message code="property.enterSumOrder"/>
    </label>
    <sf:input path="sumOrder"/>
    <sf:errors path="sumOrder" cssClass="error"/>
    <br>
    <%--not spring button--%>
    <button type="submit" name="Send">
        <s:message code="property.makeOrder"/>
    </button>

</sf:form>

</body>
</html>