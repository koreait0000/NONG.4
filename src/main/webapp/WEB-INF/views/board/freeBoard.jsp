<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<script defer src="/res/js/board/freeBoard.js"></script>
<head>
    <sec:authentication property="principal.user" var="auth"></sec:authentication>
    <title>Title</title>
</head>
<body>
<div>
    <h1>
        자유게시판
    </h1>
</div>

<div id="freeBoard" data-iuser="${auth.iuser}">
</div>

</body>
</html>