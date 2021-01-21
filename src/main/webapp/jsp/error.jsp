<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
    <title><fmt:message key="page.error.title"/></title>
</head>
<body>
<c:if test="${user ne null}">
    <jsp:include page="/jsp/common/header.jsp"/>
</c:if>
<div class="container text-center">
    <br><br>
    <c:if test="${user eq null}">
        <a href="${pageContext.request.contextPath}/jsp/login.jsp"><img src="${pageContext.request.contextPath}/images/logogreen.png" height="90px" width="105px"></a>
    </c:if>
    <br><br><br>
    <div class="row">
        <div class="col-md-offset-4 col-md-4">
            <b><fmt:message key="page.error.requestFrom"/> ${pageContext.errorData.requestURI} <fmt:message key="page.error.isFailed"/>
            <br/>
            <fmt:message key="page.error.servletName"/> ${pageContext.errorData.servletName}
            <br/>
            <fmt:message key="page.error.status"/> ${pageContext.errorData.statusCode}
            <br/>
            <fmt:message key="page.error.exception"/> ${pageContext.errorData.throwable}
            </b>
        </div>
    </div>
</div>
<jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
