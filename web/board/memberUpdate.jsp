<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <div class="wrap">
            <form name="memberUpdateFrm" method="post" action="memberUpdateProc.do">
                <table style="margin: auto">
                    <thead>
                    <tr>
                        <th>
                            회원정보수정
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>이메일(아이디)</th>
                        <td>
                            <input name="email" type="email" value="${mbean.email}" readonly>
                            <span style="color: red">*변경불가*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>별명</th>
                        <td>
                            <input name="nick_name" value="${mbean.nick_name}" readonly>
                            <span style="color: red">*변경불가*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input name="pwd" type="password" value="${mbean.pwd}">
                        </td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td>
                            <input name="name" value="${mbean.name}">
                        </td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td><input name="address" value="${mbean.address}" size="45" readonly>
                            <input name="zipcode" value="${mbean.zipcode}" size="6" readonly>
                            <input type="button" value="주소찾기" onclick="zipSearch()"><br>
                            <input name="address2" placeholder="세부주소를 입력하세요" value="${mbean.dtladdress}" size="45">
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div style="text-align: center">
                    <br>
                    <input type="reset" value="재작성">
                    <input type="button" value="회원정보변경" onclick="updateCheck()">
                </div>
            </form>
        </div>
    </section>
    <footer>
        <jsp:include page="../basic/footer.jsp"/>
    </footer>
</div>
</body>
</html>

