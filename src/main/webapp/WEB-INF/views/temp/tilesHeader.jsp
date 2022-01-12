<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav>
    <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
    <ul>
        <sec:authorize access="isAnonymous()">
            <li><a href="/board/home">메인</a></li>
            <li><a href="/board/community">커뮤니티</a></li>
            <li><a href="/user/login">로그인</a></li>
            <li><a href="/user/join">회원가입</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li><a href="/board/community">커뮤니티</a></li>
            <li><a href="/user/profile">프로필</a></li>
            <li><a href="/board/service">서비스</a></li>
            <li><a href="/user/logout">로그아웃</a></li>
        </sec:authorize>
    </ul>
</nav>