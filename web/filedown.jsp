<%--
  Created by IntelliJ IDEA.
  User: 胡建德
  Date: 2020/4/4
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<a href="${pageContext.request.contextPath}/index.html">返回首页</a><br>
<c:forEach items="${list}" var="name">
    <a href="${pageContext.request.contextPath}/filedownServlet?name=${name}">${name}</a><br>
</c:forEach>
<h1>${pageContext.request.contextPath}</h1>
</body>
</html>
