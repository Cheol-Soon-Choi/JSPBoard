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
            <div>로그인</div>
            <form name="loginFrm" action="loginProc.do" method="post" id="loginFrm">
                <div class="user_info">
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email">
                        <label for="floatingInput">이메일</label>
                    </div>
                    <div class="form-floating">
                        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="pwd">
                        <label for="floatingPassword">비밀번호</label>
                    </div>
                </div>
            </form>
            <div class="d-grid gap-2 col-6 mx-auto">
                <button type="submit" form="loginFrm" class="btn btn-success">로그인</button>
            </div>
        </div>
    </section>
    <footer>
        <jsp:include page="../basic/footer.jsp"/>
    </footer>
</div>
</body>
</html>

