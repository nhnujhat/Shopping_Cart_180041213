<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Cart</title>
</head>
<body>

<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <th>Option</th>
        <th>Id</th>
        <th>Name</th>
        <th>Photo</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Enter Quantity</th>
        <th>Total</th>
    </tr>
    <c:set var="total" value="0"></c:set>
    <c:forEach var="item" items="${sessionScope.cart}">
        <c:set var="total" value="${total + item.product.price * item.quantity }"></c:set>
        <tr>
            <td align="center">
                <a href="${pageContext.request.contextPath}/CartServlet?action=remove&id=${item.product.id}"
                   onclick="return confirm('Are you sure?')">Remove</a>
            </td>
            <td>${item.product.id}</td>
            <td>${item.product.name}</td>
            <td>
                <img src="${pageContext.request.contextPath}/${item.product.image}" width="120">
            </td>
            <td>${item.product.price}</td>
            <td>${item.quantity}</td>
            <td>
                <form method="GET" action="CartServlet?action=update&">
                    <input type="text" name="upq"><br/>
                    <input type="hidden" name="id" value="${item.product.id}"/>
                    <input type="hidden" name="action" value="update"/>
                    <input type="submit" value="Update"/>
                </form>
            </td>
            <td>${item.product.price * item.quantity}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="7" align="right">Total</td>
        <td>${total}</td>
    </tr>
</table>
<br>
<a href="${pageContext.request.contextPath}/ProductServlet">Continue Shopping</a> <br><br>

<form method="post" action="LogoutServlet">
    <input type="submit" value="Log Out">
</form>

</body>
</html>