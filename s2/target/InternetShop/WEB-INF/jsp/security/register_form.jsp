<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:mainLayout>
    <sf:form action="/register" method="post" modelAttribute="userform" cssStyle="margin-top: 20px">
        <sf:label path="username">Login:</sf:label><sf:input path="username"/><br>
        <sf:label path="email">E-mail</sf:label><sf:input path="email"/><br>
        <sf:label path="password">password: </sf:label> <sf:password  path="password"/><br>
        <sf:label path="repassword">repassword: </sf:label> <sf:password path="repassword"/><br>

        <button type="submit">Готово</button>
    </sf:form>

</t:mainLayout>
