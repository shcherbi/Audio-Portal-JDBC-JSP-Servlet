<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="icon" href="../../images/logogreen.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/headerStyle.css">
    <link rel="stylesheet" href="../../css/trackStyle.css">
    <link rel="stylesheet" href="../../css/footerStyle.css">
    <title><fmt:message key="page.trackInfo.title"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container track">
    <div class="row">
        <div class="col-xs-5 text-center">
            <img src="${track.imagePath}">
        </div>
        <div class="col-xs-7">
            <b><fmt:message key="page.trackInfo.artist"/>: </b><c:out value="${track.artist}"/>
            <br>
            <b><fmt:message key="page.trackInfo.song"/>: </b> <c:out value="${track.name}"/>
            <br>
            <b><fmt:message key="page.trackInfo.album"/>: </b><c:out value="${album.albumName}"/>
            <br>
            <b><fmt:message key="page.trackInfo.studio"/>: </b><c:out value="${album.studio}"/>
            <br>
            <b><fmt:message key="page.trackInfo.date"/>: </b><c:out value="${album.date}"/>
            <br>
            <b><fmt:message key="page.trackInfo.genre"/>: </b><c:out value="${genre.genre}"/>
            <br>
            <b><fmt:message key="page.trackInfo.price"/>: </b><c:out value="${track.price}"/>
            <br>
            <br><br>
            <audio controls controlsList="nodownload" preload="none">
                <source src="${track.linkPath}" type="audio/mpeg"/>
            </audio>
            <form method="POST" action="${pageContext.request.contextPath}/web">
                <input type="hidden" name="command" value="order_list_add">
                <button type="submit" name="orderButton" class="btn buy">
                    <fmt:message key="page.main.buy"/>
                </button>
            </form>
            <br>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 text-center">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form method="POST" action="${pageContext.request.contextPath}/web">
                        <input type="hidden" name="command" value="comment_add">
                        <textarea placeholder="<fmt:message key="page.trackInfo.commentPlaceholder"/>" name="text"></textarea>
                        ${mistakeComment}
                        <button type="submit" class="btn share"><fmt:message key="page.trackInfo.share"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="page-header">
            <h3>
                <fmt:message key="page.trackInfo.comments"/>
            </h3>
        </div>
        <c:forEach var="comment" items="${comments}">
            <div class="col-md-8">
                <div class="comments-list">
                    <div class="media">
                        <p class="pull-right">
                            <small><c:out value="${comment.date}"/></small>
                        </p>
                        <div class="media-body">
                            <h4 class="media-heading user_name"><c:out value="${comment.login}"/></h4>
                            <c:out value="${comment.text}"/>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>