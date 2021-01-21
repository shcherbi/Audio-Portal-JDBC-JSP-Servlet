<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<div class="navbar navbar-fixed-left">
    <div class="container text-center">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/user/account/account.jsp">
            <img src="${pageContext.request.contextPath}/images/logogreen.png" height="45px" width="55px">
        </a>
    </div>
    <br>
    <br>
    <br>
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/web?command=ordered_tracks"><fmt:message key="page.menu.myTracks"/></a></li>
        <li><a href="${pageContext.request.contextPath}/web?command=main"><fmt:message key="page.menu.main"/></a></li>
    </ul>
</div>
