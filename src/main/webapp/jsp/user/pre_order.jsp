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
    <c:forEach var="track" items="${orderList}">
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
                <b><fmt:message key="page.trackInfo.price"/>: </b><c:out value="${track.price}"/>
                <br>
                <form method="POST" action="${pageContext.request.contextPath}/web">
                    <input type="hidden" name="command" value="order_list_remove">
                    <button type="submit" name="orderButton" class="btn">
                        X
                    </button>
                </form>
            </div>
        </div>
        <br>
        <hr>
    </c:forEach>
    <div class="row">
        <div class="col-xs-5 text-center">
            <c:if test="${orderList.isEmpty()}">
                <b><fmt:message key="page.preOrder.cartInfo"/></b>
            </c:if>
        </div>
        <div class="col-xs-7">
            <b><fmt:message key="page.preOrder.price"/>: </b>${totalPrice}
            <br>
            <c:if test="${user.bonus ne null}">
                <b><fmt:message key="page.preOrder.priceBonus"/>: </b> ${totalPriceBonus}
                <br>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>