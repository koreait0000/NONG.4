<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>회원가입</h1>
<form action="join" method="post">
    <div><input type="email" name="email" placeholder="email" autofocus required></div>
    <div><input type="password" name="pw" placeholder="password" required></div>
    <div><input type="text" name="nm" placeholder="회원이름" required></div>
    <div><input type="text" name="nick" placeholder="닉네임" required></div>
    <div><input type="tel" name="tel" placeholder="휴대폰번호" required></div>
    <div><input type="submit" value="회원가입"><div>
</form>
