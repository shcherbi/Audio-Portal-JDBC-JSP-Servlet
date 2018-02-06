<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctag" uri="customtag" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<jsp:useBean id="orderList" class="java.util.HashSet" scope="session"/>
<header class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/web?command=main">
            <img src="../../images/logogreen.png" height="45px" width="55px">
        </a>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/jsp/user/about.jsp"><fmt:message key="page.header.about"/></a></li>
        </ul>
        <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/web">
            <input type="hidden" name="command" value="find"/>
            <div class="form-group">
                <input type="text" class="form-control" name="findText" placeholder=<fmt:message key="page.header.findPlaceholder"/>>
            </div>
            <button type="submit" class="btn find"><fmt:message key="page.header.find"/></button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <b class="allGreen">${orderList.size()}</b>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/jsp/user/account/account.jsp">${user.login}</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/web?command=logout"><fmt:message key="page.header.logout"/></a>
            </li>
        </ul>
        <div class="nav navbar-nav navbar-right">
            <div class="cart-brand">
                <a href="${pageContext.request.contextPath}/web?command=pre_order">
                    <img src="../../images/shoppingcartgreen.png" height="45px" width="55px">
                </a>
            </div>
        </div>
    </div>
</header>
<ctag:AdminTag role="${user.role}">
    <div class="navbar navbar-default navbar-fixed-top admin">
    <div class="container admin-menu">
        <a href="${pageContext.request.contextPath}/jsp/admin/add_track.jsp">
            <button type="submit" class = "btn"><fmt:message key="page.main.newTrack"/></button>
        </a>
        <a href="${pageContext.request.contextPath}/jsp/admin/add_album.jsp">
            <button type="submit" class = "btn"><fmt:message key="page.main.newAlbum"/></button>
        </a>
        <a href="${pageContext.request.contextPath}/jsp/admin/add_genre.jsp">
            <button type="submit" class = "btn"><fmt:message key="page.main.newGenre"/></button>
        </a>
        <a href="${pageContext.request.contextPath}/web?command=all_clients">
            <button type="submit" class = "btn"><fmt:message key="page.main.clients"/></button>
        </a>
    </div>
    </div>
    <br>
    <br>
</ctag:AdminTag>


