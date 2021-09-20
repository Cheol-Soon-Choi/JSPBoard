<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.BoardBean" %>
<jsp:useBean id="bMgr" class="service.BoardMgr"/>

<%
    request.setCharacterEncoding("UTF-8");

    int totalRecord = 0; // 전체 레코드 수

    int numPerPage = 10; // 페이지당 레코드 수
    int pagePerBlock = 5; //블럭당 페이지수

    int totalPage = 0; //전체 페이지 수
    int totalBlock = 0;  //전체 블럭수

    int start = 0; // db 처음 출력 순번
    int end = 10; // db 마지막 출력 순번

    int cPage = 1; // 현재페이지
    int cBlock = 1;  //현재블럭

    String keyField = "";
    String keyWord = "";
    ArrayList<BoardBean> blist = null;
    int newCount = bMgr.getTodayCount();

    if (request.getParameter("keyWord") != null) {
        keyField = request.getParameter("keyField");
        keyWord = request.getParameter("keyWord");
    }

    if (request.getParameter("cPage") != null) {
        cPage = Integer.parseInt(request.getParameter("cPage"));
    }
    start = (cPage * numPerPage) - numPerPage;
    end = numPerPage;

    totalRecord = bMgr.getCount(keyField, keyWord);
    totalPage = (int) Math.ceil((double) totalRecord / numPerPage);  //전체페이지수
    cBlock = (int) Math.ceil((double) cPage / pagePerBlock); //현재블럭 계산
    totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);  //전체블럭계산
%>

<html>
<head>
    <title>JSP 계층형 게시판</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
            crossorigin="anonymous"></script>
    <link href="style.css" rel="stylesheet" type="text/css">
    <script src="scripts.js"></script>
</head>
<body>
<div id="wrap">
    <header>
        <jsp:include page="basic/header.jsp"/>
    </header>
    <nav>
        <jsp:include page="basic/nav.jsp"/>
    </nav>
    <section>
        <div class="board_top">
            <table class="board_info">
                <tr>
                    <th style="text-align: left;">
                        <%
                            if (keyWord.equals("")) {
                        %>
                        새글: <%=newCount%> / 전체글: <%=totalRecord%><%
                    } else {%>
                        <%=keyWord%> 관련글: <%=totalRecord%> 건
                        <%
                            }
                        %></th>
                </tr>
            </table>
        </div>
        <div class="board_main table-responsive-md">
            <table class="table table-striped table-hover align-middle">
                <thead>
                <tr style="text-align: center">
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                    <th scope="col">추천</th>
                    <th scope="col">조회</th>
                </tr>
                </thead>
                <tbody>
                <%
                    blist = bMgr.getBoard(keyField, keyWord, start, end);
                    if (blist.isEmpty()) {%>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <strong>등록된 게시물이 없습니다.</strong>
                    </td>
                </tr>
                <%
                } else {
                    for (int i = 0; i < blist.size(); i++) {
                        BoardBean bean = blist.get(i);
                        int num = bean.getNum();
                        String subject = bean.getSubject();
                        String nick_name = bean.getNick_name();
                        String regdate = bean.getRegdate().substring(0, 10);
                        String category = bean.getCategory();
                        int level = bean.getLevel();
                        int like = bean.getLike();
                        int count = bean.getCount();
                %>
                <tr>
                    <th scope="row"
                        style="width: 8%; text-align: center"><%=totalRecord - ((cPage - 1) * numPerPage) - i%>
                    </th>
                    <td style="width: 45%">
                        <a href="javascript:read('<%=num%>')">
                            <% for (int j = 0; j < level; j++) {%>
                            &nbsp
                            <%
                                }
                                if (category.equals("일반")) {
                            %>
                            <%=subject%>
                            <% } else {%>
                            [<%=category%>] <%=subject%>
                            <%}%>
                        </a>
                    </td>
                    <td style="width: 15%; text-align: center"><%=nick_name%>
                    </td>
                    <td style="width: 15%; text-align: center"><%=regdate%>
                    </td>
                    <td style="width: 8%; text-align: center"><%=like%>
                    </td>
                    <td style="width: 8%; text-align: center"><%=count%>
                    </td>
                </tr>
                <% } // 게시물 for문 끝
                } // else 끝
                %>
                </tbody>
            </table>
        </div>
        <div class="board_bottom">
            <div class="paging">
                <%
                    int pageStart = (cBlock - 1) * pagePerBlock + 1; //하단 페이지 시작번호
                    int pageEnd = ((pageStart + pagePerBlock) <= totalPage) ? (pageStart + pagePerBlock) : totalPage + 1;
                    if (totalPage != 0) {%>
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <%if (cBlock > 1) {%>
                        <li class="page-item">
                            <a class="page-link" href="javascript:block('<%=cBlock-1%>')" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <%}%>
                        <%for (; pageStart < pageEnd; pageStart++) {%>
                        <li class="page-item<%if(pageStart==cPage){%> active<%}%>">
                            <a class="page-link" href="javascript:pageing('<%=pageStart %>')"><%=pageStart%>
                            </a>
                        </li>
                        <%}//for%>
                        <%if (cBlock != totalBlock) {%>
                        <li class="page-item">
                            <a class="page-link" href="javascript:block('<%=cBlock+1%>')" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <%}%>
                    </ul>
                </nav>
                <%}%>
            </div>
            <div class="button">
                <table style="width: 750px">
                    <tr>
                        <th style="width: 25%">
                            <input type="button" value="처음으로" onclick="goHome()">
                        </th>
                        <th style="width: 50%; text-align: center">
                            <form method="get" action="index.jsp" name="searchFrm" style="margin-bottom: 0px">
                                <label>
                                    <select name="keyField">
                                        <option value="nick_name">작성자</option>
                                        <option value="subject">제목</option>
                                        <option value="contents">내용</option>
                                    </select>
                                    <input type="text" name="keyWord">
                                    <input type="button" value="검색" onclick="check()"/>
                                </label>
                            </form>
                        </th>
                        <th style="width: 25%; text-align: right">
                            <input type="button" value="글쓰기" onclick="goPost()">
                        </th>
                    </tr>
                </table>
            </div>
        </div>
        <form name="readFrm" method="get">
            <input type="hidden" name="num">
            <input type="hidden" name="cPage" value="<%=cPage%>">
            <input type="hidden" name="keyField" value="<%=keyField%>">
            <input type="hidden" name="keyWord" value="<%=keyWord%>">
        </form>
    </section>
    <footer>
        <jsp:include page="basic/footer.jsp"/>
    </footer>
</div>
</body>
</html>