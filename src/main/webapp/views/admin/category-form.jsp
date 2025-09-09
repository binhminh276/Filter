<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Category Form</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${empty category.id}">
                Thêm mới Category
            </c:when>
            <c:otherwise>
                Chỉnh sửa Category
            </c:otherwise>
        </c:choose>
    </h2>

    <form method="post" action="${pageContext.request.contextPath}/${role}/category/${empty category.id ? 'add' : 'edit'}">
        <c:if test="${not empty category.id}">
            <input type="hidden" name="id" value="${category.id}"/>
        </c:if>

        <label for="name">Tên Category:</label>
        <input type="text" name="name" id="name" value="${category.name}" required/>

        <br/><br/>

        <c:choose>
            <%-- Nếu role là user thì chỉ xem, không cho submit --%>
            <c:when test="${role eq 'user'}">
                <button type="button" disabled>Không có quyền lưu</button>
            </c:when>
            <c:otherwise>
                <button type="submit">Lưu</button>
            </c:otherwise>
        </c:choose>
    </form>

    <br/>
    <a href="${pageContext.request.contextPath}/${role}/category">Quay lại danh sách</a>
</body>
</html>
