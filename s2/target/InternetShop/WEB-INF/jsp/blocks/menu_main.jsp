<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.ProductCounter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div>
        <div class="container-fluid">
            <div>
                <ul class="navbar navbar-nav">
                    <li class="active"><a href="/" class="navbar-brand">Магазин</a></li>
                    <security:authorize access="hasRole('ROLE_ANONYMOUS')">
                        <li><a href="/register">Register</a></li>
                        <li><a href="/login">Log in</a></li>
                    </security:authorize>
                    <security:authorize access="hasRole('ROLE_USER')">
                        <li>
                            <form:form action="/logout" method="POST">
                                <input type="submit" value="Logout" />
                            </form:form>
                        </li>
                        <li>
                            <label style="color: white" id="shoppingCart">  In your cart <%=request.getSession().getAttribute("shoppingCart") == null ? 0 : ((ArrayList<ProductCounter>)(request.getSession().getAttribute("shoppingCart"))).size()%> products</label>
                            <% if (request.getSession().getAttribute("shoppingCart") != null && ((ArrayList)(request.getSession().getAttribute("shoppingCart"))).size() != 0){ %>
                                <a href="/confirm_order">Buy</a>
                            <% } %>
                        </li>
                        <li>
                            <a href="/myOrders">My orders</a>
                        </li>
                    </security:authorize>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li>
                            <form:form action="/logout" method="POST">
                                <input type="submit" value="Logout" />
                            </form:form>
                        </li>
                    </security:authorize>
                </ul>
            </div>
        </div>
    </div>

</nav>








<%--<nav class="navbar navbar-fixed-top">--%>
    <%--<div class="container-fluid">--%>
        <%--<div class="collapse nav-collapse" id="bs-example-navbar-collapse-1">--%>
            <%--<ul class="nav">--%>
                <%--<li>Магазин</li>--%>
                <%--<li>Какой-то текст</li>--%>
                <%--&lt;%&ndash;<li><a href="${spring:mvcUrl('BC#list').build()}">Home</a></li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li><a href="${spring:mvcUrl('BC#add').build()}">New Book</a></li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li><a href="${spring:mvcUrl('UC#list').build()}">Users list</a></li>&ndash;%&gt;--%>
                <%--<security:authorize access="isAuthenticated()">--%>
                    <%--<li>&lt;%&ndash;<a href="${spring:mvcUrl('SC#profile').build()}">&ndash;%&gt;Profile&lt;%&ndash;</a>&ndash;%&gt;</li>--%>
                    <%--<li>&lt;%&ndash;<a href="<spring:url value="/logout" />">&ndash;%&gt;Log out&lt;%&ndash;</a>&ndash;%&gt;</li>--%>
                <%--</security:authorize>--%>
                <%--<security:authorize access="isAnonymous()">--%>
                    <%--<li>&lt;%&ndash;<a href="${spring:mvcUrl('SC#register').build()}">&ndash;%&gt;Register&lt;%&ndash;</a>&ndash;%&gt;</li>--%>
                    <%--<li>&lt;%&ndash;<a href="<spring:url value="/username"/>">&ndash;%&gt;Log in&lt;%&ndash;</a>&ndash;%&gt;</li>--%>
                <%--</security:authorize>--%>
                <%--&lt;%&ndash;<li><a href="<c:url value='/about' />">About</a></li>&ndash;%&gt;--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</nav>--%>