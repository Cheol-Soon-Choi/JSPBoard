<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>우편번호 검색</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/scripts.js"></script>
</head>
<body style="background-color: bisque">
<div class="wrap" style="margin-top: 10px; text-align: center">
    <form name="zipFrm" method="post" action="zipSearch.do">
        <div>
            <span style="color: red">*서울특별시 검색만 가능*</span><br>
            <br>도로명 입력: <input name="area3">
            <input type="button" value="검색" onclick="loadArea()"><br>
        </div>
        <br>
        <div>
            <c:choose>
                <c:when test="${search=='y'}">
                    <c:choose>
                        <c:when test="${empty zlist}">
                            <strong>검색 결과가 없습니다.</strong><br>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${zlist}" var="list">
                                <a href="javascript:void(0)"
                                   onclick="sendTotalArea(${list.zipcode}, '${list.area1}', '${list.area2}', '${list.area3}'); return false;">
                                        ${list.area1} ${list.area2} ${list.area3}
                                    <br></a>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <div>
            <br><input type="button" onclick="window.close()" value="닫기"/>
        </div>
        <input type="hidden" name="search" value="y">
    </form>
</div>
</body>
</html>