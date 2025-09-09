<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Manager Home</title></head>
<body>
<h2>Xin chào Manager: ${sessionScope.user.username}</h2>
<a href="${pageContext.request.contextPath}/logout">Logout</a> |
<a href="${pageContext.request.contextPath}/manager/category">Quản lý Category</a>
<hr/>

<h3>Danh sách Category của bạn</h3>
<table border="1">
    <tr><th>ID</th><th>Tên</th></tr>
    <c:forEach var="c" items="${categories}">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
