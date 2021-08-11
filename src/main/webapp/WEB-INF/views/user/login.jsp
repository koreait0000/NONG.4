<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="login-page">
    <div class="social-login">
        <div class="login-google pointer">구글</div>
        <div class="login-naver pointer">네이버</div>
        <div class="login-kakao pointer">카카오</div>
        <div class="login-facebook pointer">페이스북</div>
        <div class="login-nong4 pointer">농사</div>
    </div>

    <form id="u-login" action="/login" method="post">
        <div>
            <div><input type="email" name="email" placeholder="email" autofocus required></div>
            <div><input type="password" name="pw" placeholder="password" required></div>
            <div><input type="submit" value="로그인"></div>
        </div>
        <div><a href="join">회원가입</a></div>
    </form>
</div>
