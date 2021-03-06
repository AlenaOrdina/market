
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<header class="header__wrapper">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <div class="header__logo">
        <a href="${contextPath}/"></a>
    </div>
    <%--  <div class="header__contacts">
          <div class="contacts__item">
              <a href="mailto:mail@mail.ru" class="link link_contact">mail@mail.ru</a>
          </div>
          <div class="contacts__item">
              <a href="tel:88005553535" class="link link_contact">88005553535</a>
          </div>
      </div>--%>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <ul class="header__authorization">
            <li>
                <a href="${contextPath}/bucket" class="link link_header"><img src="${contextPath}/img/shopping-cart.png"></a>
            </li>
            <li>
                <a href="${contextPath}/user/login" class="link link_header">Login</a>
            </li>
            <li>
                <a href="${contextPath}/user/registration" class="link link_header">Registration</a>
            </li>
        </ul>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <ul class="header__authorization">
            <security:authorize access="hasRole('ROLE_ADMIN')">
              <li>
                  <a href="${contextPath}/admin" class="link link_header">Аdmin</a>
              </li>
            </security:authorize>
            <li>
                <a href="${contextPath}/order/history" class="link link_header">Orders</a>
            </li>
            <li>
                <a href="${contextPath}/bucket" class="link link_header"><img src="${contextPath}/img/shopping-cart.png"></a>
            </li>
            <li>
                <a href="${contextPath}/user/account" class="link link_header">Аccount</a>
            </li>
            <li>
                <a class="link link_header" onclick="document.forms['logoutForm'].submit()">Logout</a>
            </li>

        </ul>
    </c:if>
    <nav class="header__menu">
        <ul class="menu__wrapper">
            <li>
                <a href="${contextPath}/" class="link link_header">Home</a>
            </li>
            <li>
                <a href="${contextPath}/products" class="link link_header">Product</a>
            </li>
           <%-- <li class="nav-item"> <a class="nav-link cart-item-count" href="${contextPath}/bucket" data-cesta-feira-items-count><span class="fa fa-shopping-cart"></span> Shopping Cart</a> </li>--%>

            <li>
                <a href="http://localhost:8080/stand" class="link link_header">Dashboard</a>
            </li>
        </ul>

    </nav>

</header>
