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
    <link rel="icon" href="${pageContext.request.contextPath}/images/logogreen.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trackStyle.css">
    <script rel="script" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="${pageContext.request.contextPath}/js/validator.js"></script>
    <title><fmt:message key="page.allClients.title"/></title>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"/>
<div class="container track">
    <div class="row">
        <div class="page-header">
            <h3>
                <fmt:message key="page.allClients.clients"/>
            </h3>
        </div>
        <c:forEach var="client" items="${clients}">
            <div class="row">
                <div class="col-sm-8">
                    <div class="comments-list">
                        <div class="media">
                            <div class="media-body">
                                <h4 class="media-heading user_name"><fmt:message key="page.allClients.login"/>:</h4>
                                <c:out value="${client.login}"/>
                                <h4 class="media-heading user_name"><fmt:message key="page.allClients.email"/>:</h4>
                                <c:out value="${client.email}"/>
                                <h4 class="media-heading user_name"><fmt:message key="page.allClients.bonus"/>:</h4>
                                <c:out value="${client.bonus}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <a href="${pageContext.request.contextPath}/web?command=grant_user&login=${client.login}">
                        <fmt:message key="page.allClients.grant"/>
                    </a>
                    <form autocomplete="off" onsubmit="return checkBonus()" action="${pageContext.request.contextPath}/web?command=set_bonus&login=${client.login}" method="POST">
                        <input type="text" class="form-control" name="bonus" placeholder=<fmt:message key="page.allClients.bonus"/>>
                        <button type="submit" name="setBonusButton" class="btn"><fmt:message key="page.allClients.bonusAdd"/></button>
                    </form>
                    <a href="${pageContext.request.contextPath}/web?command=delete_user&login=${client.login}">
                        <fmt:message key="page.allClients.delete"/>
                    </a>
                    <br>
                    <b>${adminFunctionalError}<b>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>