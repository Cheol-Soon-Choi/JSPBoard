<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    let msg;
    let url;
    let alertType = ${alertType};

    switch (alertType) {
        // 일반 = 메시지 출력 후 페이지 전환
        case 1:
            msg = "${msg}";
            url = "${url}";
            alert(msg);
            location.href = url;
            break;
        // 경고 = 메시지 출력 후 뒤로가기
        case 2:
            msg = "${msg}";
            alert(msg);
            history.back();
            break;
        // 팝업 = 메시지 출력 후 팝업창 닫고, 부모창 페이지 전환
        case 3:
            msg = "${msg}";
            url = "${url}";
            alert(msg);
            opener.parent.location = url;
            window.close();
            break;
    }
</script>