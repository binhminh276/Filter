<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách Category</title>
    <style>
        table {
            border-collapse: collapse;
            width: 70%;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background: #f0f0f0;
        }
    </style>
</head>
<body>
    <h2>Danh sách Category</h2>

    <!-- Chỉ admin và manager mới được thêm -->
    <c:if test="${role eq 'admin' || role eq 'manager'}">
        <a href="${pageContext.request.contextPath}/${role}/category/add">➕ Thêm Category</a>
        <br/><br/>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên Category</th>
                <th>Người tạo</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${categories}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.user.username}</td>
                    <td>
                        <c:choose>
                            <%-- Admin: sửa/xóa tất cả --%>
                            <c:when test="${role eq 'admin'}">
                                <a href="${pageContext.request.contextPath}/admin/category/edit?id=${c.id}">✏️ Sửa</a>
                                |
                                <a href="${pageContext.request.contextPath}/admin/category/delete?id=${c.id}"
                                   onclick="return confirm('Xóa category này?');">🗑️ Xóa</a>
                            </c:when>

                            <%-- Manager: chỉ sửa/xóa category của mình --%>
                            <c:when test="${role eq 'manager' && c.user.id == sessionScope.user.id}">
                                <a href="${pageContext.request.contextPath}/manager/category/edit?id=${c.id}">✏️ Sửa</a>
                                |
                                <a href="${pageContext.request.contextPath}/manager/category/delete?id=${c.id}"
                                   onclick="return confirm('Xóa category này?');">🗑️ Xóa</a>
                            </c:when>

                            <%-- User: chỉ được xem --%>
                            <c:otherwise>
                                (Chỉ xem)
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br/>
    <a href="${pageContext.request.contextPath}/${role}/home">🏠 Về trang chủ</a>
</body>
</html>
