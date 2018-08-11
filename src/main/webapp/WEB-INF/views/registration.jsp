<%--
  Created by IntelliJ IDEA.
  User: miro
  Date: 08.04.18
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>market-web</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="registration__main">
        <form:form  method='POST' modelAttribute="customer" class="registration__wrapper">
            <h2>Create account</h2>
            <dl class="dl_class">
                <spring:bind path="email">
                <dt>
                    E-mail*
                </dt>
                <dd >
                    <form:input type="text" path="email" id="email" class="email"
                                placeholder="email"/>
                    <p>You must enter e-mail.</p>
                </dd>
                </spring:bind>
                <spring:bind path="parole">
                <dt>
                    Пароль*
                </dt>
                <dd }>
                    <form:input type="password" path="parole" id="parole"
                                placeholder="password"/>
                    <p>You must enter password.</p>
                </dd>
                </spring:bind>
                <spring:bind path="paroleConfirm">
                <dt>
                    Repeat password*
                </dt>
                <dd >
                    <form:input type="password" path="paroleConfirm" id="paroleConfirm"
                                placeholder="password confirm"/>
                    <p>Repeat password. Passwords must match.</p>
                </dd>
                </spring:bind>
            </dl>
        <c:if test="${error!=null}">
            <div class="error">
                <span>${error}</span>
            </div>
        </c:if>
            <input type='submit' class="button19" value='Check in'>

        </form:form>
    </main>
</div>
</body>
</html>
