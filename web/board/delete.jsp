<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/scripts.js"></script>
<body style="background-color: bisque">
<div class="wrap" style="margin-top: 50px; text-align: center">
    <div>
        <strong>게시글 비밀번호를 입력하세요</strong>
    </div>
    <form name="delFrm" method="post" action="deleteProc.bo">
        <div>
            <input type="password" name="inpwd">
        </div>
        <div>
            <input type="button" value="확인" onclick="delCheck()"/>
            <input type="button" onclick="window.close()" value="취소"/>
            <input type="hidden" name="num" value='${num}'/>
            <input type="hidden" name="cPage" value='${cPage}'/>
        </div>
    </form>
</div>
</body>
</html>