<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:mainLayout title="Add product">

    <sf:form action="/admin/addProduct" method="post" modelAttribute="productform" cssStyle="margin-top: 20px">
        <sf:label path="productName">Name:</sf:label><sf:input path="productName"/><br>
        <sf:label path="cost">Cost:</sf:label><sf:input path="cost"/><br>
        <sf:label path="warehouse">Warehouse:</sf:label>
        <form:select path="warehouse">
            <c:forEach items="${warehouses}" var="warehouse">
                <form:option value="${warehouse.id}"> ${warehouse.city} - ${warehouse.address} </form:option>
            </c:forEach>
        </form:select>
        <br>
        <sf:label path="amount">Amount:</sf:label><sf:input path="amount"/><br>
        <sf:label path="description">Description:</sf:label><sf:textarea path="description"/><br>
        <button type="submit">Готово</button>
    </sf:form>
</t:mainLayout>
