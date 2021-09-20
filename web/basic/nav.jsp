<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>nav</title>
</head>
<body>
<div class="user_info">
    <c:choose>
        <c:when test="${empty nick}">
            <strong>Guest </strong>님, 접속
        </c:when>
        <c:otherwise>
            <strong>${nick} </strong>님, 접속
        </c:otherwise>
    </c:choose>
</div>
<c:if test="${empty idKey}">
    <ul class="nav nav-fill nav-tabs">
        <li class="nav-item">
            <a class="nav-link <c:if test="${pageContext.request.servletPath == '/board/main.jsp'}">active</c:if>"
               href="${pageContext.request.contextPath}/board/main.do">게시판</a></li>
        <li class="nav-item">
            <a class="nav-link <c:if test="${pageContext.request.servletPath == '/board/member.jsp'}">active</c:if>"
               href="${pageContext.request.contextPath}/board/member.do">회원가입</a></li>
        <li class="nav-item">
            <a class="nav-link <c:if test="${pageContext.request.servletPath == '/board/login.jsp'}">active</c:if>"
               href="${pageContext.request.contextPath}/board/login.do">로그인</a></li>
    </ul>
</c:if>
<c:if test="${!empty idKey}">
    <ul class="nav nav-fill nav-tabs">
        <li class="nav-item">
            <a class="nav-link <c:if test="${pageContext.request.servletPath == '/board/main.jsp' || pageContext.request.servletPath.contains('/board/read.jsp')}">active</c:if>"
               href="${pageContext.request.contextPath}/board/main.do">게시판</a></li>
        <li class="nav-item">
            <a class="nav-link <c:if test="${pageContext.request.servletPath == '/board/member.jsp'}">active</c:if>"
               href="${pageContext.request.contextPath}/board/member.do">회원가입</a></li>
        <li class="nav-item">
            <a class="nav-link"
               href="${pageContext.request.contextPath}/board/logout.do">로그아웃</a></li>
        <li class="nav-item">
            <a class="nav-link <c:if test="${pageContext.request.servletPath == '/board/memberUpdate.jsp'}">active</c:if>"
               href="${pageContext.request.contextPath}/board/memberUpdate.do">회원정보수정</a></li>
    </ul>
</c:if>
</body>
</html>
