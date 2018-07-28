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
    <meta charset="UTF-8">
    <title>market-web</title>
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
                    <dt>
                        Country
                    </dt>
                    <dd>
                        <form:input type="text" path="country"
                                    placeholder="Country"/>
                        <p>Indicate country.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="region">
                    <dt>
                        Region
                    </dt>
                    <dd>
                        <form:input type="text" path="region"
                                    placeholder="Region"/>
                        <p>Indicate region.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="city">
                    <dt>
                        City
                    </dt>
                    <dd>
                        <form:input type="text" path="city"
                                    placeholder="City"/>
                        <p>Indicate city.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="street">
                    <dt>
                        Street
                    </dt>
                    <dd>
                        <form:input type="text" path="street"
                                    placeholder="Street"/>
                        <p>Indicate street.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="building">
                    <dt>
                        Building
                    </dt>
                    <dd>
                        <form:input type="text" path="building"
                                    placeholder="Building"/>

                        <p>Indicate number of building</p>
                    </dd>
                </spring:bind>
                <spring:bind path="apartment">
                    <dt>
                        Apartment
                    </dt>
                    <dd>
                        <form:input type="text" path="apartment"
                                    placeholder="Apartment"/>

                        <p>Indicate number of apartment if you have.</p>
                    </dd>
                </spring:bind>
                <spring:bind path="postcode">
                    <dt>
                        Postcode
                    </dt>
                    <dd>
                        <form:input type="text" path="postcode"
                                    placeholder="Postcode"/>

                        <p>Indicate postcode.</p>
                    </dd>
                </spring:bind>
            </dl>
            <c:if test="${error!=null}">
                <div class="error">
                    <span>${error}</span>
                </div>
            </c:if>
            <input type='submit' value='Add'>
        </form:form>
    </main>
</div>
</body>
</html>
