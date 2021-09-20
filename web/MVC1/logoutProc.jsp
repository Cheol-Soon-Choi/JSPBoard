<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
session.invalidate();
%>
<script>
    alert('로그아웃 되었습니다.')
    location.href="index.jsp"
</script>
