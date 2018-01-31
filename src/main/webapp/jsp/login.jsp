<%@ page errorPage="/jsp/error.jsp" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="icon" href="../../images/logogreen.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/loginStyle.css">
    <link rel="stylesheet" href="../css/footerStyle.css">
    <title><fmt:message key="page.login.title"/></title>
</head>
<body>
<div class="navbar-fixed-top">
    <div class="jumbotron text-center">
        <h1><img src="../images/logogreen.png" height="90px" width="105px">Imaginarium Music</h1>
    </div>
</div>
<div class="container-fixed">
    <form class="form-signIn" method="POST" action="${pageContext.request.contextPath}/web">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="nickname" class="form-control" placeholder="<fmt:message key="page.login.login"/>">
        <input type="password" name="password" class="form-control" placeholder="<fmt:message key="page.login.password"/>">
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <button type="submit" name="enterButton" class="btn"><fmt:message key="page.login.signIn"/></button>
        <a href="${pageContext.request.contextPath}/jsp/register.jsp"><fmt:message key="page.login.registration"/></a>
    </form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
