<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/headerStyle.css">
    <link rel="stylesheet" href="../../css/aboutStyle.css">
    <link rel="stylesheet" href="../../css/footerStyle.css">
    <title><fmt:message key="page.about.title"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container about">
    <div class="row">
        <div class="col-sm-8">
            <div class="well">
                <h3><fmt:message key="page.about.address"/>:</h3>
                <p>30 Seaside Terrace<br>
                    Santa Monica, CA 90401<br>
                    USA</p>
                <br/>
                <br/>
                <h3><fmt:message key="page.about.email"/>:</h3>
                <p>ilia.scherbakov42@gmail.com</p>
                <br/>
                <br/>
                <h3><fmt:message key="page.about.phone"/>:</h3>
                <p>+1-202-555-0172</p>
            </div>
        </div>
        <div class="col-sm-6">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4190.8145110465475!2d-118.49751566889523!3d34.00957084339392!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x80c2a4d6ceaa7d25%3A0x3f53cd4c2c25e921!2zMzAgU2Vhc2lkZSBUZXJyYWNlLCBTYW50YSBNb25pY2EsIENBIDkwNDAxLCDQodCo0JA!5e1!3m2!1sru!2sby!4v1516920584879" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>
