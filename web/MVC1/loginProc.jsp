<%@ page import="bean.MemberBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mMgr" class="service.MemberMgr"/>
<%
    request.setCharacterEncoding("UTF-8");
    String email = request.getParameter("email");
    String pwd = request.getParameter("pwd");
    String msg = "";
    String url = "login.jsp";

    int result = mMgr.login(email, pwd);
    MemberBean mBean = mMgr.getMember(email);
    if (result == 1) {
        msg = mBean.getNick_name() + "님 로그인에 성공하였습니다.";
        url = "index.jsp";
        session.setAttribute("idKey", email);
        session.setAttribute("nick", mBean.getNick_name());
    } else if (result == 0) {
        msg = "비밀번호를 다시한번 확인해주세요.";
    } else {
        msg = "아이디를 다시한번 확인해주세요.";
    }
%>
<script>
    alert("<%=msg%>");
    if ('<%=result%>' == 1) {
        location.href = "<%=url%>";
    } else {
        history.back();
    }
</script>
