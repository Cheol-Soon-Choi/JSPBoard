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
        <form name="editFrm" action="editProc.bo" method="post" style="width: 750px; margin: auto">
            <table style="width: 750px">
                <tr>
                    <td>
                        <select name="category">
                            <option value="일반" <c:if test="${category == '일반'}">selected</c:if>>일반</option>
                            <option value="유머" <c:if test="${category == '유머'}">selected</c:if>>유머</option>
                            <option value="경제" <c:if test="${category == '경제'}">selected</c:if>>경제</option>
                        </select>
                        <input type="text" name="subject" value="${subject}">
                    </td>
                </tr>
                <tr>
                    <td>
                <textarea name="contents" id="contents" rows="10" cols="100"
                          style="width: 100%; height: 400px">${contents}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="inpwd" placeholder="비밀번호를 입력하세요"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <input type="hidden" name="num" value="${num}">
                        <input type="hidden" name="cPage" value="${cPage}">
                        <input type="button" value="취소" onclick="history.back()">
                        <input type="button" value="등록" onclick="editSubmit()">
                    </td>
                </tr>
            </table>
        </form>
    </section>
    <footer>
        <jsp:include page="../basic/footer.jsp"/>
    </footer>
</div>
</body>
</html>

