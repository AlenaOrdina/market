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
    <title>Hard Candy</title>
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="registration__main">
        <form:form  method='POST' modelAttribute="customer" class="registration__wrapper">
            <h2>Sign up using your Email address </h2>
            <dl class="dl_class">
                <spring:bind path="email">

                <dd >
                    <form:input type="text"  path="email" id="email" class="vvod"
                                placeholder="Email*"/>
                </dd>
                </spring:bind>
                <spring:bind path="parole">
                <dd>
                    <form:input type="password" path="parole" id="parole" class="vvod"
                                placeholder="Password"/>
                </dd>
                </spring:bind>
                <spring:bind path="paroleConfirm">

                <dd>
                    <form:input type="password" class="vvod" path="paroleConfirm" id="paroleConfirm"
                                placeholder="Password confirm"/>
                    <p>*All fields required</p>
                </dd>
                </spring:bind>
            </dl>
        <c:if test="${error!=null}">
            <div class="error">
                <span>${error}</span>
            </div>
        </c:if>
            <input type='submit' class="knopka01" value='Register'>

        </form:form>
    </main>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
