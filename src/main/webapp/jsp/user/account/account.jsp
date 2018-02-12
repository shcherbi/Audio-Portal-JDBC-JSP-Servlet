<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="icon" href="../../../images/logogreen.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/menuStyle.css">
    <link rel="stylesheet" href="../../../css/accountStyle.css">
    <script rel="script" src="../../js/jquery-3.3.1.min.js"></script>
    <script rel="script" src="../../js/validator.js"></script>
    <title><fmt:message key="page.menu.myAccount"/></title>
</head>
<body>
<jsp:include page="../../../jsp/common/menu.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3><fmt:message key="page.account.login"/>:</h3><br>
            ${user.login}
        </div>
        <div class="col-sm-4">
            <h3><fmt:message key="page.account.email"/>:</h3><br>
            ${user.email}
        </div>
        <div class="col-sm-4">
            <c:if test="${user.bonus ne null}">
                <h3><fmt:message key="page.account.bonus"/>:</h3><br>
                ${user.bonus}
            </c:if>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm-4">
            <form autocomplete="off" method="POST" onsubmit="return checkNewLogin()" action="${pageContext.request.contextPath}/web?command=change_login">
                <input type="text" name="nickname" class="form-control" placeholder="<fmt:message key="page.account.newLogin"/>">
                <br>
                <br>
                <button type="submit" name="changeLogin" class="btn">
                    <fmt:message key="page.account.change"/>
                </button>
            </form>
            <b>${changeLoginError}</b>
        </div>
        <div class="col-sm-4">
            <form autocomplete="off" method="POST" onsubmit="return checkNewEmail()" action="${pageContext.request.contextPath}/web?command=change_email">
                <input type="text" name="email" class="form-control" placeholder="<fmt:message key="page.account.newEmail"/>">
                <br>
                <br>
                <button type="submit" name="changeEmail" class="btn">
                    <fmt:message key="page.account.change"/>
                </button>
            </form>
            <b>${changeEmailError}</b>
        </div>
        <div class="col-sm-4">
            <form autocomplete="off" method="POST" onsubmit="return checkNewPassword()" action="${pageContext.request.contextPath}/web?command=change_password">
                <input type="text" name="password" class="form-control" placeholder="<fmt:message key="page.account.newPassword"/>">
                <br>
                <br>
                <button type="submit" name="changePassword" class="btn">
                    <fmt:message key="page.account.change"/>
                </button>
            </form>
            <b>${changePasswordError}</b>
        </div>
    </div>
</div>
</body>
</html>