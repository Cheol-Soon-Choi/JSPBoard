<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="boardMgr" class="service.BoardMgr"/>
<%
    request.setCharacterEncoding("UTF-8");
    int num = Integer.parseInt(request.getParameter("num"));
    boardMgr.upLike(num);
    response.sendRedirect("read.jsp?num=" + num);
%>

