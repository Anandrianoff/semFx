<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:mainLayout>
    <sf:form action="/login/process" method="post" cssStyle="margin-top: 20px ">
        <%--<sf:errors>--%>
        <input type="text" class="form-control" id="username" placeholder="Логин" name="username">
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Пароль" name="password">
        <%--</sf:errors>--%>
        <%--<sf:label path="username">Login:</sf:label><sf:input path="username"/><br>--%>
        <%--<sf:label path="password">Password</sf:label><sf:password path="password"/>--%>
        <button type="submit">Go</button>
    </sf:form>
</t:mainLayout>

