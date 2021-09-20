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
            <form name="regFrm" method="post" action="memberProc.do">
                <table style="margin: auto">
                    <thead>
                    <tr>
                        <th>
                            신규 등록
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>이메일(아이디)</th>
                        <td>
                            <input name="email" type="email">
                            <input type="button" value="중복확인" onclick="idCheck(document.regFrm.email.value)">
                        </td>
                    </tr>
                    <tr>
                        <th>별명</th>
                        <td>
                            <input name="nick_name">
                            <input type="button" value="중복확인" onclick="nickCheck(document.regFrm.nick_name.value)">
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input name="pwd" type="password">
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호 확인</th>
                        <td>
                            <input name="pwd2" type="password">
                        </td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td>
                            <input name="name">
                        </td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td><input name="address" readonly size="45">
                            <input name="zipcode" readonly size="6">
                            <input type="button" value="주소찾기" onclick="zipSearch()"><br>
                            <input name="address2" placeholder="세부주소를 입력하세요" size="45">
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div style="text-align: center">
                    <br>
                    <input type="reset" value="재작성">
                    <input type="button" value="회원가입" onclick="inputCheck()">
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

