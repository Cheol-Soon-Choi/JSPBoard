<%@ page import="bean.BoardBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bMgr" class="service.BoardMgr"/>
<%
    request.setCharacterEncoding("UTF-8");
    int inpwd = Integer.parseInt(request.getParameter("inpwd"));
    int num = Integer.parseInt(request.getParameter("num"));
    String cPage = request.getParameter("cPage");

    BoardBean bean = (BoardBean) session.getAttribute("bean");
    int dbpwd = bean.getPass();
    if (dbpwd == inpwd) {
        bMgr.delBoard(num);
        out.println("<script>alert('삭제 완료')</script>");
        out.println("<script>opener.parent.location=`index.jsp?cPage=" + cPage + "`</script>");
        out.println("<script>window.close()</script>");
    } else {
        out.println("<script>alert('게시글 비밀번호가 일치하지 않습니다.')</script>");
    }
%>