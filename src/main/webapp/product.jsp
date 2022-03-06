<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Product List</title>
</head>
<body>

<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Photo</th>
        <th>Price</th>
        <th>Add to Cart</th>
    </tr>
    <c:forEach var="product" items="${productArray}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>
                <img src="${pageContext.request.contextPath}/${product.image}" width="120">
            </td>
            <td>${product.price}</td>
            <td align="center">
                <a href="${pageContext.request.contextPath}/CartServlet?&action=buy&id=${product.id}&name=${product.name}&price=${product.price}&image=${product.image}">Add to Cart</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>

<form method="get" action="CartServlet">
    <input type="submit" value="Your Cart">
</form>
<form method="post" action="LogoutServlet">
    <input type="submit" value="Log Out">
</form>

</body>
</html>