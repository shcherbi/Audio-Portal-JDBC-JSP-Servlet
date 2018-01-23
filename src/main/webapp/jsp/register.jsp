<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/registerStyle.css">
    <link rel="stylesheet" href="../css/footerStyle.css">
    <title><fmt:message key="page.registration.title"/></title>
</head>
<body>
<div class="container text-center">
    <br><br>
    <a href="${pageContext.request.contextPath}/jsp/login.jsp"><img src="../images/logogreen.png" height="90px" width="105px"></a>
    <br><br><br>
    <div class="row">
        <div class="col-md-offset-4 col-md-4">
            <form class="form-register" action="${pageContext.request.contextPath}/web" method="POST">
                <input type="hidden" name="command" value="registration" />
                <input type="text" name="nickname" class="form-control" placeholder="<fmt:message key="page.registration.login"/>">
                <br>
                <input type="password" name="password" class="form-control" placeholder="<fmt:message key="page.registration.password"/>">
                <br>
                <input type="email" name="email" class="form-control" placeholder="<fmt:message key="page.registration.email"/>">
                <br>
                <button type="submit" name="registrationButton" class="btn"><fmt:message key="page.registration.signUp"/></button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
