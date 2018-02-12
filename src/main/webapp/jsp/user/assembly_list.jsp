<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctag" uri="customtag" %>
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
    <title><fmt:message key="page.assemblyList.title"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="findError">
    ${mistakeSongName}
</div>
<div class="container">
    <div class="row display-flex">
        <c:if test="${assemblyList.isEmpty()}">
        <div class="col-xs-5 text-center">
                <b><fmt:message key="page.trackInfo.assemblyListEmpty"/></b>
        </div>
        </c:if>
        <c:forEach var="assembly" items="${assemblyList}">
            <div class="col-sm-4">
                <div class="thumbnail text-center">
                    <div class="card-body">
                        <h4 class="card-title">
                            <b><c:out value="${assembly.name}"/>
                        </h4>
                        <a href="${pageContext.request.contextPath}/web?command=assembly_info&assembly=${assembly.name}">
                            <button type="submit" name="assemblyInfo" class="btn">
                                ...
                            </button>
                        </a>
                        <ctag:AdminTag role="${user.role}">
                            <a href="${pageContext.request.contextPath}/web?command=delete_assembly&assembly=${assembly.id}">
                                <button type="submit" name="deleteAssembly" class="btn">
                                    X
                                </button>
                            </a>
                        </ctag:AdminTag>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
