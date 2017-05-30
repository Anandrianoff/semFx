<%@tag description="Default Layout Tag" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" %>

<!DOCTYPE HTML>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <c:if test="${not empty title}" >${title}</c:if>
    </title>

    <%--<script type="text/javascript" src="<c:url value="/js/jquery-2.1.3.min.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js" />"></script>--%>

    <link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />" rel="stylesheet">
    <%--<link href="<c:url value="/css/style.css" />" rel="stylesheet">--%>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/blocks/menu_main.jsp"/>
<div class="container">
    <c:if test="${not empty message}">
        <div class="global-message ${empty messageClass ? '' :  messageClass}">${message}</div>
    </c:if>
    <br>
    <br>
    <br>
    <jsp:doBody/>

    <jsp:include page="/WEB-INF/jsp/blocks/footer.jsp"/>
</div>
</body>
</html>