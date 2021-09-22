//게시물 검색
function check() {
    let text = document.searchFrm.keyWord.value;
    if (text.replace(/\s | /gi, "").length == 0) {
        alert("검색어를 입력하세요")
        document.searchFrm.keyWord.focus();
        return;
    }
    if (document.searchFrm.keyWord.value.length > 10) {
        alert("10글자 이하로 검색해주세요")
        document.searchFrm.keyWord.focus();
        return;
    }
    document.searchFrm.submit();
}

//처음으로
function goHome() {
    location.href = "main.do";
}

//글쓰기
function goPost() {
    location.href = "post.bo"
}

//글 등록
function textSubmit() {
    let title = document.postFrm.subject.value;
    let contents = document.postFrm.contents.value;
    let pattern = /[0-9]/;
    let pass = document.postFrm.pass.value;
    if (title.replace(/\s | /gi, "").length == 0) {
        alert('제목을 입력하세요')
        document.postFrm.subject.focus()
        return;
    }

    if (contents.replace(/\s | /gi, "").length == 0) {
        alert("내용을 입력하세요.")
        document.postFrm.contents.focus()
        return;
    }
    if (pass.replace(/\s | /gi, "").length == 0) {
        alert("비밀번호를 입력하세요.")
        document.postFrm.pass.focus()
        return;
    }
    if (pattern.test(pass) == false || pass.length != 4) {
        alert("비밀번호는 숫자 4자리로 구성해야합니다.")
        return;
    }
    document.postFrm.submit();
}

//회원가입
function inputCheck() {
    let exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    let email = document.regFrm.email.value;
    let nick_name = document.regFrm.nick_name.value;
    let pwd = document.regFrm.pwd.value;
    let pwd2 = document.regFrm.pwd2.value;
    let name = document.regFrm.name.value;
    let address = document.regFrm.address.value;

    if (email.replace(/\s | /gi, "").length == 0) {
        alert("이메일(아이디)을 입력해 주세요.");
        document.regFrm.email.focus();
        return;
    } else if (exptext.test(email) == false) {
        alert("이메일 형식이 올바르지 않습니다.");
        document.regFrm.email.focus();
        return;
    }
    if (nick_name.replace(/\s | /gi, "").length == 0) {
        alert("별명을 입력해주세요.");
        document.regFrm.nick_name.focus();
        return;
    }
    if (pwd.replace(/\s | /gi, "").length == 0) {
        alert("비밀번호를 입력해주세요.");
        document.regFrm.pwd.focus();
        return;
    }
    if (pwd2.replace(/\s | /gi, "").length == 0) {
        alert("비밀번호 확인을 위해 한번 더 입력해주세요.");
        document.regFrm.pwd2.focus();
        return;
    }

    if (pwd2 != pwd) {
        alert("비밀번호가 일치하지 않습니다.");
        document.regFrm.pwd2.value = "";
        document.regFrm.pwd2.focus();
        return;
    }
    if (name.replace(/\s | /gi, "").length == 0) {
        alert("이름을 입력해주세요.");
        document.regFrm.name.focus();
        return;
    }
    if (address.replace(/\s | /gi, "").length == 0) {
        alert("주소를 검색해주세요.");
        return;
    }
    document.regFrm.submit();
}

//id 중복확인
function idCheck(email) {
    let exptext2 = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    if (email.replace(/\s | /gi, "").length == 0) {
        alert("이메일(아이디)을 입력해 주세요.");
        document.regFrm.email.focus();
        return;
    } else if (exptext2.test(email) == false) {
        alert("이메일 형식이 올바르지 않습니다.");
        document.regFrm.email.focus();
        return;
    }
    window.open("idCheck.do?email=" + email, 'idCheck', 'width=450, height=150');
}

//별명 중복확인
function nickCheck(nick_name) {
    if (nick_name.replace(/\s | /gi, "").length == 0) {
        alert("별명을 입력해주세요.");
        document.regFrm.nick_name.focus();
        return;
    }
    window.open("nickCheck.do?nick_name=" + nick_name, 'nickCheck', 'width=450, height=150');
}

//주소검색창 클릭
function zipSearch() {
    window.open("zipSearch.do?search=n", "ZipCodeSearch", "width=500,height=300,scrollbars=yes");
}

//주소 검색
function loadArea() {
    area = document.zipFrm.area3.value
    if (area.replace(/\s | /gi, "").length == 0) {
        alert("주소를 입력해 주세요.");
        document.zipFrm.area3.focus();
        return;
    }
    document.zipFrm.action = "zipSearch.do"
    document.zipFrm.submit();
}

//주소 검색 후 부모창에 전달
function sendTotalArea(zipCode, area1, area2, area3) {
    let totalArea = area1 + " " + area2 + " " + area3;
    if (opener.document.location.pathname == "/JSPBoard/board/memberUpdate.do") {
        opener.document.memberUpdateFrm.zipcode.value = zipCode;
        opener.document.memberUpdateFrm.address.value = totalArea;
        self.close();
    } else if (opener.document.location.pathname == "/JSPBoard/board/member.jsp") {
        opener.document.regFrm.zipcode.value = zipCode;
        opener.document.regFrm.address.value = totalArea;
        self.close();
    }
}

//게시물 읽기
function read(num) {
    document.readFrm.num.value = num;
    document.readFrm.action = "read.bo";
    document.readFrm.submit();
}

//삭제 확인
function delCheck() {
    let pass = document.delFrm.inpwd.value;
    if (pass.replace(/\s | /gi, "").length == 0) {
        alert("게시물 비빌번호를 입력해주세요");
        document.delFrm.inpwd.focus();
        return;
    }
    document.delFrm.submit();
}

//수정 확인
function editSubmit() {
    let pass = document.editFrm.inpwd.value;
    if (pass.replace(/\s | /gi, "").length == 0) {
        alert("게시물 비빌번호를 입력해주세요");
        document.editFrm.inpwd.focus();
        return;
    }
    document.editFrm.submit();
}

//답글 확인
function replySubmit() {
    let pass = document.replyFrm.inpwd.value;
    if (pass.replace(/\s | /gi, "").length == 0) {
        alert("게시물 비빌번호를 입력해주세요");
        document.replyFrm.inpwd.focus();
        return;
    }
    document.replyFrm.submit();
}

//회원정보수정
function updateCheck() {
    let pwd = document.memberUpdateFrm.pwd.value;
    let name = document.memberUpdateFrm.name.value;
    let address = document.memberUpdateFrm.address.value;

    if (pwd.replace(/\s | /gi, "").length == 0) {
        alert("비밀번호를 입력해주세요.");
        document.memberUpdateFrm.pwd.focus();
        return;
    }
    if (name.replace(/\s | /gi, "").length == 0) {
        alert("이름을 입력해주세요.");
        document.memberUpdateFrm.name.focus();
        return;
    }
    if (address.replace(/\s | /gi, "").length == 0) {
        alert("주소를 검색해주세요.");
        return;
    }
    document.memberUpdateFrm.submit();
}

//페이지 이동
function pageing(page) {
    document.readFrm.cPage.value = page;
    document.readFrm.submit();
}

//이전, 이후 블록 이동
function block(value) {
    document.readFrm.cPage.value = 5 * (value - 1) + 1;
    document.readFrm.submit();
}

//다운로드
function down(filename) {
    document.downLoadFrm.filename.value = filename;
    document.downLoadFrm.submit();
}

//좋아요
function like() {
    let num = $(".num").attr('value');
    let data = {"num": num};
    $.ajax({
        type: "POST",
        url: "likeProc.do",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (response){
            let num = response.num;
            $("#like_count").html(num);
        }
    })
}