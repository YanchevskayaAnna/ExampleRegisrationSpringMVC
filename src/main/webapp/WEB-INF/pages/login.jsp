<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login jsp</title>
    <style>
        .error{
            color:#ff0000;
        }
    </style>
</head>
<body>
<%--it is a link to RegistrationController method doRegistration()--%>
<c:set var="urladdr" value="userlogin"/>
<%--Spring form--%>
<%-- modelAttribute values is important --%>
<sf:form modelAttribute="registeredUser" action="${urladdr}">
    <%--spring label--%>
   <label for="login">
        <s:message code="property.enterYourLogin"/>
    </label>
    <sf:input path="login"/>
    <sf:errors path="login" cssClass="error"/>
    <br>
    <label for="password">
        <s:message code="property.enterYourPassword"/>
    </label>
    <sf:input path="password"/>
    <sf:errors path="password" cssClass="error"/>
    <br>
    <%--not spring button--%>
    <button type="submit" name="Send">
        <s:message code="property.sendToLogin"/>
    </button>

</sf:form>

</body>
</html>