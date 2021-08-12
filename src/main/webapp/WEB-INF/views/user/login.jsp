<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="login-page">
    <h1 style="margin-bottom: 50px; text-align: center;">Nong4</h1>
    <div class="social-login">
        <input type="button" value="구글" class="login-google pointer">
        <input type="button" value="네이버" class="login-naver pointer">
        <input type="button" value="카카오" class="login-kakao pointer">
        <input type="button" value="페이스북" class="login-facebook pointer">
        <input type="button" value="농사" class="login-nong4 pointer" onclick="loginForm()">
    </div>

    <form id="u-login" action="/login" method="post" style="visibility: hidden;">
        <div>
            <div><input class="u-login-email" type="email" name="email" placeholder="email" autofocus required></div>
            <div><input class="u-login-pw" type="password" name="pw" placeholder="password" required></div>
            <div><input class="u-login-submit pointer" type="submit" value="로그인"></div>
            <div><a href="join">회원가입</a></div>
        </div>
    </form>
</div>
