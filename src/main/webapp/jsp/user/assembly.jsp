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
    <link rel="stylesheet" href="../../css/mainStyle.css">
    <link rel="stylesheet" href="../../css/footerStyle.css">
    <script rel="script" src="../../js/mainScript.js"></script>
    <title><fmt:message key="page.main.title"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="findError">
    ${mistakeSongName}
</div>
<div class="container">
    <div class="row display-flex">
        <c:if test="${tracks.isEmpty()}">
            <div class="col-xs-5 text-center">
                <b><fmt:message key="page.trackInfo.assemblyEmpty"/></b>
            </div>
        </c:if>
        <c:forEach var="track" items="${tracks}">
            <div class="col-sm-3">
                <div class="thumbnail text-center">
                    <div class="card-body">
                        <h4 class="card-title">
                            <b><c:out value="${track.artist}"/> -
                                <br><c:out value="${track.name}"/></b>
                        </h4>
                        <h4 class="card-price">
                            <c:out value="${track.price}"/> $
                        </h4>
                        <div class="text-center">
                            <img src="${track.imagePath}">
                            <br><br>
                            <audio controls controlsList="nodownload" preload="none">
                                <source src="${track.linkPath}" type="audio/mpeg"/>
                            </audio>
                        </div>
                        <br>
                        <a href="${pageContext.request.contextPath}/web?command=track_info&track=${track.id}">
                            <button type="submit" name="trackInfo" class="btn">
                                ...
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
