<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logogreen.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerStyle.css">
    <script rel="script" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="${pageContext.request.contextPath}/js/validator.js"></script>
    <title><fmt:message key="page.addAlbum.title"/></title>
</head>
<body>
<div class="container text-center">
    <br><br>
    <a href="${pageContext.request.contextPath}/web?command=main">
        <img src="${pageContext.request.contextPath}/images/logogreen.png" height="90px" width="105px">
    </a>
    <br><br><br>
    <div class="row">
        <div class="col-md-offset-4 col-md-4">
            <form autocomplete="off" onsubmit="return checkAlbum()" class="form-register" action="${pageContext.request.contextPath}/web" method="POST">
                <input type="hidden" name="command" value="add_album"/>
                <input type="text" name="album" class="form-control"
                       placeholder="<fmt:message key="page.addTrack.album"/>">
                <br>
                <input type="text" name="studio" class="form-control"
                       placeholder="<fmt:message key="page.addTrack.studio"/>">
                <br>
                <input type="text" name="date" class="form-control"
                       placeholder="<fmt:message key="page.addTrack.date"/>">
                <br>
                <button type="submit" name="addAlbumButton" class="btn">
                    <fmt:message key="page.addAlbum.add"/>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
