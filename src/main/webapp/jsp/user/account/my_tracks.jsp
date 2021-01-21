<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logogreen.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trackStyle.css">
    <title><fmt:message key="page.menu.myTracks"/></title>
</head>
<body>
<jsp:include page="/jsp/common/menu.jsp"/>
<div class="container">
    <c:if test="${orderedTrack.isEmpty()}">
        <div class="col-xs-5 text-center">
            <b><fmt:message key="page.myTrack.empty"/></b>
        </div>
    </c:if>
    <c:forEach var="track" items="${orderedTrack}">
    <br>
    <div class="row">
        <div class="col-xs-5 text-center">
            <img src="${track.imagePath}">
        </div>
        <div class="col-xs-7">
            <b><fmt:message key="page.trackInfo.artist"/>: </b><c:out value="${track.artist}"/>
            <br>
            <b><fmt:message key="page.trackInfo.song"/>: </b> <c:out value="${track.name}"/>
            <br>
            <audio controls controlsList="nodownload" preload="none">
                <source src="${track.linkPath}" type="audio/mpeg"/>
            </audio>
            <a href="${track.linkPath}">
                <button type="submit" name="orderButton" class="btn">
                    <fmt:message key="page.myTrack.download"/>
                </button>
            </a>
        </div>
    </div>
    <br>
    <hr>
    </c:forEach>
</div>
</body>
</html>
