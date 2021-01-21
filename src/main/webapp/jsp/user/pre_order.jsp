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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trackStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
    <script rel="script" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="${pageContext.request.contextPath}/js/validator.js"></script>
    <title><fmt:message key="page.trackInfo.title"/></title>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"/>
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
                <a href="${pageContext.request.contextPath}/web?command=order_list_remove&track=${track.id}">
                    <button type="submit" name="cancelOrderButton" class="btn">
                        X
                    </button>
                </a>
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
            <c:if test="${!orderList.isEmpty()}">
                <fmt:message key="page.preOrder.infoText"/>
                <form autocomplete="off" method="POST" onsubmit="return checkCard()" action="${pageContext.request.contextPath}/web">
                    <input type="hidden" name="command" value="order"/>
                    <div class="form-group">
                        <br>
                        <br>
                        <input type="text" class="form-control" name="cardNumber" placeholder=<fmt:message
                                key="page.preOrder.cardNumber"/>>
                        <br>
                        <br>
                        <input type="text" class="form-control" name="svcCode" placeholder=<fmt:message
                                key="page.preOrder.svcCode"/>>
                        <br>
                        <br>
                    </div>
                    <button type="submit" class="btn purchase"><fmt:message key="page.preOrder.buy"/></button>
                </form>
                <br>
                <br>
                ${purchaseRejected}
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>