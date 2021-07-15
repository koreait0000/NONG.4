<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>
        자유게시판
    </title>
</head>
<body>
    <p>principal : <sec:authentication property="principal" /></p>
    <p>UserEntity : <sec:authentication property="principal.user" /></p>
    <p>PK : <sec:authentication property="principal.user.iuser" /></p>
    <h1>자유게시판</h1>
</body>
</html>