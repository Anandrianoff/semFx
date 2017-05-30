<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout>

    <sf:form action="/admin/warehouses/add" method="post" modelAttribute="warehouseform" cssStyle="margin-top: 20px">
        <sf:label path="city">City:</sf:label><sf:input path="city"/><br>
        <sf:label path="address">Address:</sf:label><sf:input path="address"/><br>

        <button type="submit">Готово</button>
    </sf:form>

    <ul>
    <c:forEach items="${warehouses}" var="warehouse">
        <li>${warehouse.city} - ${warehouse.address}</li>
    </c:forEach>
    </ul>
</t:mainLayout>
