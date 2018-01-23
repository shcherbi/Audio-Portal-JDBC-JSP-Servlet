<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="properties.content"/>
<html>
<footer class="navbar-fixed-bottom">
        <div class="row">
            <div class="col-xs-8">
                <b>Imaginarium Music Corporation © 2018. All Rights Reserved</b>
            </div>
            <div class="col-xs-4">
                <b><a href="${pageContext.request.contextPath}/web?command=language&lang=ru_RU">RU</a></b>
                <b><a href="${pageContext.request.contextPath}/web?command=language&lang=be_BE">BE</a></b>
                <b><a href="${pageContext.request.contextPath}/web?command=language&lang=">EN</a></b>
            </div>
        </div>
</footer>
</html>
