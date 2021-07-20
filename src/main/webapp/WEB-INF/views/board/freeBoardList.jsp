<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<script defer src="/res/js/board/freeBoard.js"></script>
<sec:authentication property="principal.user" var="auth"></sec:authentication>
<head>
    <title>Title</title>
</head>
<body>
<h1>gsdsdgdsgdssdg</h1>

<c:forEach var="data" items="${data}">
<div id="freeBoardList" data-iuser="${auth.iuser}" data-iboard="${data.iboard}" >
<%--    <div>--%>
<%--        <c:out value="${data.iboard}" />--%>
<%--    </div>--%>
    <div>
        ${data.iboard}
    </div>
</c:forEach>
</div>

</body>
</html>