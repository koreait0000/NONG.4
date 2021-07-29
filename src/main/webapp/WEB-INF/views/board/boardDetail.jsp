<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="kategorie">
            <img src="/res/img/main.png">
            <div style="font-size: 50px; font-weight: bold"> Nong 4</div>
            <div><input type="text" class="searchtxt"><i class="fas fa-search pointer"></i></div>
            <div><a href="friendBoard"><button class="writeBtn pointer"><i class="fas fa-pen"></i>글쓰기</button></a></div>
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
                    <div class="ctnt">${detail.ctnt}</div>
                    <c:forEach items="${img}" var="img">
                        <div>
                           <div> ${img.iboard}</div>
                        </div>
                    </c:forEach>
                </div>
                <div class="cmtbox">
                    <form action="insCmt" method="post">
                        <input type="hidden" name="iboard" value="${param.iboard}">
                        <textarea cols="120" rows="5" class="cmttext" name="cmt" placeholder="댓글을 입력하세요 ※위치 맞춰야함"></textarea>
                        <input type="submit" class="cmtbtn" value="댓글달기">
                    </form>
                </div>
                <div class="cmtlist">
                    <c:forEach items="${cmt}" var="cmt">
                        <div>
                            <div class="cmtUserNick">${cmt.userNick}</div>
                            <div class="cmt">${cmt.cmt}</div>
                            <div class="cmtRegdt">${cmt.regdt}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>