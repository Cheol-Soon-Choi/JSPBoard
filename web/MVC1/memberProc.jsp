<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="mgr" class="service.MemberMgr"/>
<jsp:useBean id="bean" class="bean.MemberBean"/>
<jsp:setProperty name="bean" property="*"/>

<%
    boolean result = mgr.insertMember(bean);
    String msg = "회원가입에 실패하였습니다.";
    String location = "member.jsp";
    if (result) {
        msg = "회원가입이 완료되었습니다.";
        location = "login.jsp";
    }
%>

<script>
    alert("<%=msg%>");
    location.href="<%=location%>";
</script>