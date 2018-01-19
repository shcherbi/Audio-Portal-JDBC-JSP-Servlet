<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/footerStyle.css">
</head>
<footer class="navbar-fixed-bottom">
        <div class="row">
            <div class="col-xs-8">
                Imaginarium Music Corporation © 2018. All Rights Reserved
            </div>
            <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/web?command=language&lang=ru_RU">RU</a>
                <a href="${pageContext.request.contextPath}/web?command=language&lang=be_BE">BE</a>
                <a href="${pageContext.request.contextPath}/web?command=language&lang=">EN</a>
            </div>
        </div>
</footer>
</html>
