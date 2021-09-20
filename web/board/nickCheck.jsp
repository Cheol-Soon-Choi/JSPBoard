<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/scripts.js"></script>
<body style="background-color: bisque">
<div class="wrap" style="margin-top: 50px; text-align: center">
    <c:choose>
        <c:when test="${result == true}">
            별명: <strong>${nick}</strong>는 이미 존재합니다.
        </c:when>
        <c:otherwise>
            별명: <strong>${nick}</strong>는 사용 가능합니다.
        </c:otherwise>
    </c:choose>
    <br><br>
    <div>
        <input type="button" onclick="window.close()" value="확인"/>
    </div>
</div>
</body>
</html>