<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.user" var="auth"></sec:authentication>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="kategorie">
            <img src="/res/img/main.png">
            <div style="font-size: 50px; font-weight: bold"> Nong 4</div>
            <div><input type="text" class="searchtxt"><i class="fas fa-search pointer"></i></div>
            <div><button class="writeBtn pointer"><a href="friendBoard"><i class="fas fa-pen"></i>글쓰기</a></button></div>
            <div class="kategorieitem1">
                <div class="title">알림판 <i class="fas fa-list"></i></div>
                <div><a href="">공지사항</a></div>
                <div><a href="">이벤트</a></div>
            </div>
            <div class="kategorieitem2">
                <div class="title">커뮤니티 <i class="fas fa-list"></i></div>
                <div><a href="">통합 게시판</a></div>
                <div><a href="friendBoardList?provider=freedom">자유 게시판</a></div>
                <div><a href="friendBoardList?provider=question">질문 게시판</a></div>
                <div><a href="friendBoardList?provider=strategy">공략 게시판</a></div>
                <div><a href="friendBoardList?provider=friend">친구 게시판</a></div>
            </div>
        </div>
        <div class="community">
            <div class="community-top"></div>
            <div class="communityboard">
                <div>
                    <div>
                        <c:choose>
                            <c:when test="${detail.provider == 'freedom'}">
                                <div>[자유게시판]</div>
                            </c:when>
                            <c:when test="${detail.provider == 'question'}">
                                <div>[질문게시판]</div>
                            </c:when>
                            <c:when test="${detail.provider == 'strategy'}">
                                <div>[공략게시판]</div>
                            </c:when>
                            <c:otherwise>
                                <div>[친구게시판]</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="title">${detail.title}</div>
                    <div class="userinfo">
                        <div>${detail.userNick}</div>
                        <div>${detail.regdt}</div>
                    </div>
                    <div>${detail.ctnt}</div>
                </div>
                <div>댓글창하고 넣을꺼임 </div>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>