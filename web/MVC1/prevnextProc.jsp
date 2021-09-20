<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bMgr" class="service.BoardMgr"/>
<%
    request.setCharacterEncoding("UTF-8");
    int num = Integer.parseInt(request.getParameter("num"));
    String movingP = request.getParameter("movingP");
    String cPage = request.getParameter("cPage");
    int rownum = 0;

    if (movingP.equals("이전글")) { // 이전글
        rownum = bMgr.prevBoard(num);
        if (rownum != 0) {
            response.sendRedirect("read.jsp?cPage=" + cPage + "&num=" + rownum);
        } else {
            out.println("<script>alert('첫 글입니다')</script>");
            out.println("<script>history.back()</script>");
        }
    } else { // 다음글
        rownum = bMgr.nextBoard(num);
        if (rownum != 0) {
            response.sendRedirect("read.jsp?cPage=" + cPage + "&num=" + rownum);
        } else {
            out.println("<script>alert(`마지막 글입니다`)</script>");
            out.println("<script>history.back()</script>");
        }
    }
%>