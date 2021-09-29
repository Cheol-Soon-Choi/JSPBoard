<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>
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
        <jsp:include page="../basic/header.jsp"/>
    </header>
    <nav>
        <jsp:include page="../basic/nav.jsp"/>
    </nav>
    <section>
        <div class="wrap">
            <div class="readBoard_top">
                <div style="font-size: x-large">
                    [${bean.category}] <strong>${bean.subject}</strong>
                </div>
                <div style="border-bottom: dimgray solid 1px;">
                    <span style="margin-right: 10px;">
                    <strong style="font-size: large">${bean.nick_name}</strong></span>
                    <span style="margin-right: 10px; color: dimgray">
                        등록: ${bean.regdate.substring(0, 19)}</span>
                    <c:if test="${!empty bean.edtdate}">
                         <span style="margin-right: 10px; color: dimgray">
                        수정: ${bean.edtdate.substring(0, 19)}</span>
                    </c:if>
                    <span style="margin-right: 10px; color: dimgray">
                        좋아요: <span id="like_count">${bean.like}</span></span>
                    <span style="margin-right: 10px; color: dimgray">
                        조회: ${bean.count}</span>
                </div>
            </div>
            <br/>
            <div class="readBoard_main">
                <textarea name="contents" id="contents" rows="10" cols="100"
                          style="border: none; width: 100%; height: 400px"
                          readonly>${bean.contents}</textarea>
                <div style="margin-top: 15px">
                    첨부파일:
                    <c:choose>
                        <c:when test="${bean.file_name != null && bean.file_name != ''}">
                            <a href="javascript:void(0)" onclick="down('${bean.file_name}')">${bean.file_name}</a>
                            <span>(${bean.file_size/1000.0} KB)</span>
                        </c:when>
                        <c:otherwise>
                            파일 없음.
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="readBoard_bottom" style="border-top: dimgray 1px solid; width: 100%;">
                <table style="width: 750px">
                    <tr>
                        <form name="prevnextFrm" method="get" action="prevnextProc.do">
                            <td style="width: 50%; text-align: left">
                                <button type="submit" name="movingP" value="이전글">이전글</button>
                            </td>
                            <td style="width: 50%; text-align: right;">
                                <button type="submit" name="movingP" value="다음글">다음글</button>
                            </td>
                            <input name="num" type="hidden" value="${num}">
                            <input name="cPage" type="hidden" value="${cPage}">
                        </form>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <c:choose>
                                <c:when test="${check == false}">
                                    <a class="read_btn" id="blackHeart" href="javascript:void(0)"
                                       onclick="like()">좋아요🖤</a>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${flag == false}">
                                            <a class="read_btn" id="blackHeart" href="javascript:void(0)"
                                               onclick="like()">좋아요🖤</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="read_btn" id="redHeart" href="javascript:void(0)"
                                               onclick="like()">좋아요❤</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                            <input class="email" name="email" type="hidden" value="${email}">
                            <input class="num" name="num" type="hidden" value="${num}">
                            <a class="read_btn" href="reply.bo?cPage=${cPage}&num=${num}">답글</a>
                            <c:if test="${email !=null && email == bean.email}">
                                <a class="read_btn" href="edit.bo?cPage=${cPage}&num=${num}">수정</a>
                                <a class="read_btn" href="delete.bo?cPage=${cPage}&num=${num}"
                                   onclick="window.open(this.href, 'delBoard', 'width=500, height=250'); return false;">삭제</a>
                            </c:if>
                            <a class="read_btn"
                               href="main.do?cPage=${cPage}&num=${num}&keyField=${keyField}&keyWord=${keyWord}">목록</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </section>
    <form name="listFrm" method="post" action="main.do">
        <input type="hidden" name="nowPage" value="${cPage}">
        <c:if test="${keyWord != null && keyWord != ''}">
            <input type="hidden" name="keyField" value="${keyField}">
            <input type="hidden" name="keyWord" value="${keyWord}">
        </c:if>
    </form>
    <form name="downLoadFrm" method="post" action="downLoad.do">
        <input type="hidden" name="filename">
    </form>
    <footer>
        <jsp:include page="../basic/footer.jsp"/>
    </footer>
</div>
</body>
</html>

