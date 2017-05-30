<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:mainLayout>

    <security:authorize access="hasRole('ROLE_ADMIN')">
        <a href="admin/addProduct">Add product</a><br>
        <a href="admin/warehouses">Add warehouse</a><br>
        <a href="/admin/users">Users</a><br>
        <a href="/admin/orders">Orders</a>
    </security:authorize>
    <c:forEach items="${products}" var="product">
        <div style="margin: 5px; background-color: inherit">
            <a href="/product?id=${product.id}"><h3>${product.name}</h3></a>
        </div>
    </c:forEach>

</t:mainLayout>
