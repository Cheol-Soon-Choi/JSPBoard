<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/scripts.js"></script>
</head>
<body>
<div id="wrap">
    <header>
        <jsp:include page="/basic/header.jsp"/>
    </header>
    <nav>
        <jsp:include page="/basic/nav.jsp"/>
    </nav>
    <section>
        <div class="board_top">
            <table class="board_info">
                <tr>
                    <th style="text-align: left;">
                        <c:choose>
                            <c:when test="${keyWord == ''}">
                                새글: ${newCount} / 전체글: ${pb.totalRecord}
                            </c:when>
                            <c:otherwise>
                                ${keyWord} 관련글: ${pb.totalRecord} 건
                            </c:otherwise>
                        </c:choose>
                    </th>
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
                <c:choose>
                    <c:when test="${empty blist}">
                        <tr>
                            <td colspan="6" style="text-align: center">
                                <strong>등록된 게시물이 없습니다.</strong>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="blist" items="${blist}" varStatus="vs">
                            <tr>
                                <th scope="row"
                                    style="width: 8%; text-align: center">
                                        ${pb.totalRecord - ((pb.cPage - 1) * pb.numPerPage) - vs.index}
                                </th>
                                <td style="width: 45%">
                                    <a href="javascript:read('${blist.num}')">
                                        <c:forEach begin="0" end="${blist.level}">
                                            &nbsp
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${blist.category == '일반'}">
                                                ${blist.subject}
                                            </c:when>
                                            <c:otherwise>
                                                [${blist.category}] ${blist.subject}
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </td>
                                <td style="width: 15%; text-align: center">${blist.nick_name}
                                </td>
                                <td style="width: 15%; text-align: center">${blist.regdate.substring(0, 10)}
                                </td>
                                <td style="width: 8%; text-align: center">${blist.like}
                                </td>
                                <td style="width: 8%; text-align: center">${blist.count}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
        <div class="board_bottom">
            <div class="paging">
                <jsp:include page="paging.jsp"/>
            </div>
            <div class="button">
                <table style="width: 750px">
                    <tr>
                        <th style="width: 25%">
                            <input type="button" value="처음으로" onclick="goHome()">
                        </th>
                        <th style="width: 50%; text-align: center">
                            <form method="get" action="main.do" name="searchFrm" style="margin-bottom: 0px">
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
            <input type="hidden" name="cPage" value="${cPage}">
            <input type="hidden" name="keyField" value="${keyField}">
            <input type="hidden" name="keyWord" value="${keyWord}">
        </form>
    </section>
    <footer>
        <jsp:include page="/basic/footer.jsp"/>
    </footer>
</div>
</body>
</html>