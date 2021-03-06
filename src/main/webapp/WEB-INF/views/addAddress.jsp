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


<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Hard Candy</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/app.css">
    <link rel="shortcut icon" href="${contextPath}/img/favicon.png" type="image/png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<div class="bg__wrapper"></div>
<div class="container__wrapper">
    <%@include file="header.jsp"%>
    <main class="registration__main">
        <form:form  method='POST' modelAttribute="address" class="registration__wrapper"
                    action="${contextPath}/user/add_address">
            <h2>Add address</h2>
            <dl class="dl_class">
                <spring:bind path="country">
                    <dd>
                        <form:input type="text" class="vvod" path="country"
                                    placeholder="Country*"/>
                    </dd>
                </spring:bind>
                <spring:bind path="region">

                    <dd>
                        <form:input type="text" class="vvod" path="region"
                                    placeholder="Region*"/>
                    </dd>
                </spring:bind>
                <spring:bind path="city">

                    <dd>
                        <form:input type="text" class="vvod" path="city"
                                    placeholder="City*"/>
                    </dd>
                </spring:bind>
                <spring:bind path="street">

                    <dd>
                        <form:input type="text" class="vvod" path="street"
                                    placeholder="Street*"/>
                    </dd>
                </spring:bind>
                <spring:bind path="building">

                    <dd>
                        <form:input type="text" class="vvod" path="building"
                                    placeholder="Building*"/>
                    </dd>
                </spring:bind>
                <spring:bind path="apartment">
                    <dd>
                        <form:input type="text" class="vvod"  path="apartment"
                                    placeholder="Apartment"/>
                    </dd>
                </spring:bind>
                <spring:bind path="postcode">
                    <dd>
                        <form:input type="text" class="vvod" path="postcode"
                                    placeholder="Postcode*"/>
                    </dd>
                    <p>*All fields required</p>
                </spring:bind>
            </dl>
            <c:if test="${error!=null}">
                <div class="error">
                    <span>${error}</span>
                </div>
            </c:if>
            <input type='submit' class="knopka01" value='Add'>
        </form:form>
    </main>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
