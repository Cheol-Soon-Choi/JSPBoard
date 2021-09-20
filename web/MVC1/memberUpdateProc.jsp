<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="mMgr" class="service.MemberMgr"/>
<jsp:useBean id="memberbean" class="bean.MemberBean"/>
<jsp:setProperty name="memberbean" property="*"/>
<%
    String address2 = request.getParameter("address2");
    memberbean.setDtladdress(address2);
    boolean rs = mMgr.updateMember(memberbean);
    if (rs) {
        out.println("<script>alert('수정이 완료되었습니다.')</script>");
        out.println("<script>window.location.href='index.jsp'</script>");
    } else {
        out.println("<script>alert('수정에 실패하였습니다.')</script>");
        out.println("<script>history.back()</script>");
    }
%>