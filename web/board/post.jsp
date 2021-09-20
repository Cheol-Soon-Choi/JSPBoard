<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>등록</title>
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
        <form name="postFrm" action="postProc.bo" method="post" style="width: 750px; margin: auto"
              enctype="multipart/form-data">
            <table style="width: 750px">
                <tr>
                    <td>
                        <select name="category">
                            <option value="일반">일반</option>
                            <option value="유머">유머</option>
                            <option value="경제">경제</option>
                        </select>
                        <input type="text" name="subject" placeholder="제목을 입력하세요"/>
                        <input type="hidden" name="email" value="${email}">
                    </td>
                </tr>
                <tr>
                    <td>
                <textarea name="contents" id="contents" rows="10" cols="100"
                          style="width: 100%; height: 400px"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="file" name="filename" size="50" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="pass" placeholder="비밀번호를 입력하세요"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <input type="button" value="취소" onclick="goHome()">
                        <input type="button" value="등록" onclick="textSubmit()">
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