<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Paging</title>
</head>
<body>
<c:if test="${pb.totalPage != 0}">
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <div>
                <c:if test="${pb.cBlock > 1}">
                    <li class="page-item">
                        <a class="page-link" href="javascript:block('${pb.cBlock-1}')" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
            </div>
            <div>
                <c:forEach var="i" begin="${pb.pageStart}" end="${pb.pageEnd-1}">
                    <li class="page-item <c:if test="${i == pb.cPage}"> active</c:if>">
                        <a class="page-link" href="javascript:pageing('${i}')">${i}
                        </a>
                    </li>
                </c:forEach>
            </div>
            <div>
                <c:if test="${pb.cBlock != pb.totalBlock}">
                    <li class="page-item">
                        <a class="page-link" href="javascript:block('${pb.cBlock+1}')" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </div>

        </ul>
    </nav>
</c:if>
</body>
</html>
