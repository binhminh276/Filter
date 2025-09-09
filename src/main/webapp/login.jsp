<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
<h2>Đăng nhập</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/login">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username" required><br/><br/>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required><br/><br/>

    <button type="submit">Đăng nhập</button>
</form>
</body>
</html>
