<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="сtag" uri="customtag" %>
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
    <script rel="script" src="../../js/mainScript.js"></script>
    <title><fmt:message key="page.trackInfo.title"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container track">
    <div class="row">
        <div class="col-xs-5 text-center">
            <img src="${track.imagePath}">
            <br>
            <сtag:AdminTag role="${user.role}">
                <form method="POST" action="${pageContext.request.contextPath}/web?command=change_image_link">
                    <input type="text" name="imageLink" class="form-control"
                           placeholder="<fmt:message key="page.addTrack.linkImage"/>">
                    <button type="submit" name="changeImageLinkButton" class="btn change">
                        <fmt:message key="page.trackInfo.change"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
        </div>
        <div class="col-xs-7">
            <b><fmt:message key="page.trackInfo.artist"/>: </b><c:out value="${track.artist}"/>
            <br>
            <сtag:AdminTag role="${user.role}">
                <form method="POST" action="${pageContext.request.contextPath}/web?command=change_artist">
                    <input type="text" name="artist" class="form-control"
                           placeholder="<fmt:message key="page.trackInfo.artist"/>">
                    <button type="submit" name="changeArtistButton" class="btn change">
                        <fmt:message key="page.trackInfo.change"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
            <b><fmt:message key="page.trackInfo.song"/>: </b><c:out value="${track.name}"/>
            <br>
            <сtag:AdminTag role="${user.role}">
                <form method="POST" action="${pageContext.request.contextPath}/web?command=change_track_name">
                    <input type="text" name="name" class="form-control"
                           placeholder="<fmt:message key="page.trackInfo.song"/>">
                    <button type="submit" name="changeSongNameButton" class="btn change">
                        <fmt:message key="page.trackInfo.change"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
            <b><fmt:message key="page.trackInfo.album"/>: </b><c:out value="${album.albumName}"/>
            <br>
            <b><fmt:message key="page.trackInfo.studio"/>: </b><c:out value="${album.studio}"/>
            <br>
            <b><fmt:message key="page.trackInfo.date"/>: </b><c:out value="${album.date}"/>
            <br>
            <сtag:AdminTag role="${user.role}">
                <form method="POST" action="${pageContext.request.contextPath}/web?command=change_album">
                    <input type="text" name="album" class="form-control"
                           placeholder="<fmt:message key="page.trackInfo.album"/>">
                    <input type="text" name="studio" class="form-control"
                           placeholder="<fmt:message key="page.trackInfo.studio"/>">
                    <input type="text" name="date" class="form-control"
                           placeholder="<fmt:message key="page.trackInfo.date"/>">
                    <button type="submit" name="changeAlbumButton" class="btn change">
                        <fmt:message key="page.trackInfo.change"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
            <b><fmt:message key="page.trackInfo.genre"/>: </b><c:out value="${genre.genre}"/>
            <br>
            <сtag:AdminTag role="${user.role}">
                <form method="POST" action="${pageContext.request.contextPath}/web?command=change_genre">
                    <input type="text" name="genre" class="form-control"
                           placeholder="<fmt:message key="page.trackInfo.genre"/>">
                    <button type="submit" name="changeGenreButton" class="btn change">
                        <fmt:message key="page.trackInfo.change"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
            <b><fmt:message key="page.trackInfo.price"/>: </b><c:out value="${track.price}"/>
            <br>
            <сtag:AdminTag role="${user.role}">
                <form method="POST" action="${pageContext.request.contextPath}/web?command=change_price">
                    <input type="text" name="price" class="form-control"
                           placeholder="<fmt:message key="page.trackInfo.price"/>">
                    <button type="submit" name="changePriceButton" class="btn change">
                        <fmt:message key="page.trackInfo.change"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
            <br>
            <сtag:AdminTag role="${user.role}">
                <form method="POST" action="${pageContext.request.contextPath}/web?command=change_link">
                    <input type="text" name="link" class="form-control"
                           placeholder="<fmt:message key="page.addTrack.link"/>">
                    <button type="submit" name="changeLinkButton" class="btn change">
                        <fmt:message key="page.trackInfo.change"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
            <сtag:AdminTag role="${user.role}">
                <br>
                <form method="POST" action="${pageContext.request.contextPath}/web?command=delete_track&track=${track.id}">
                    <button type="submit" name="changeLinkButton" class="btn delete">
                        <fmt:message key="page.trackInfo.delete"/>
                    </button>
                </form>
                <br>
            </сtag:AdminTag>
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
            ${isOrdered}
            ${updateAudioTrackError}
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