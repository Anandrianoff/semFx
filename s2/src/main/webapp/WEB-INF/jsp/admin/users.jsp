<%@ page import="model.User" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:mainLayout title="Users">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>User Id</th>
                <th>Username</th>
                <th>E-mail</th>
                <th>Confirmed</th>
                <th>Orders total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                        <form action="/admin/removeUser" method="post">
                            ${user.id}
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>">
                                <button onclick="submit" style="color: red">X</button>
                        </form>
                        <%--<button onclick="remove(${user.id}, ${token})" style="color: red">X</button>--%>
                    </td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>
                        <form action="/admin/changeConfirmation" method="post">
                            ${user.emailConfirmed}
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>">
                            <button type="submit">change</button>
                        </form>
                    </td>
                    <td>${user.orders.size()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.js"></script>
    <script type="text/javascript">
        function unconfirm(id, token) {
            $.post('ajax.php', {'id':id})
//                    function(data) {
//                        $('#result').html(data);
//                    });

        }
    </script>
</t:mainLayout>


