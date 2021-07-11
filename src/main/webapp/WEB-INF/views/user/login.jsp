<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Login
    </title>
</head>
<body>
<h1>로그인<h1>
    <form action="/login" method="post">
        <div><input type="email" name="email" placeholder="email" autofocus required></div>
        <div><input type="password" name="pw" placeholder="password" required></div>
        <div><input type="submit" value="로그인"><div>
    </form>
    <div><a href="join">회원가입</a></div>
<body>
</html>