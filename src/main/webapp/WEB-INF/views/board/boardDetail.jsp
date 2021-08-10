<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.user" var="auth"></sec:authentication>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="category">
            <img src="/res/img/main.png">
            <div style="font-size: 50px; font-weight: bold"> Nong 4</div>

            <div class="search-input">
                <input type="text" class="search-txt">
                <i class="fas fa-search pointer"></i>
            </div>

            <div>
                <button class="writeBtn pointer">
                    <a href="boardWrite"><i class="fas fa-pen"></i>글쓰기</a>
                </button>
            </div>

            <div class="cate-event">
                <div class="title">알림판<i class="fas fa-list"></i></div>
                <div><a href="">공지사항</a></div>
                <div><a href="">이벤트</a></div>
            </div>

            <div class="cate-board">
                <div class="title">커뮤니티 <i class="fas fa-list"></i></div>
                <div><a href="mainBoard">통합 게시판</a></div>
                <div><a href="mainBoard?provider=freedom">자유 게시판</a></div>
                <div><a href="mainBoard?provider=question">질문 게시판</a></div>
                <div><a href="mainBoard?provider=strategy">공략 게시판</a></div>
                <div><a href="mainBoard?provider=friend">친구 게시판</a></div>
            </div>
        </div>
        <div class="community" data-hit-count="${detail.hitCount}">
            <div class="community-top"></div>
            <div class="community-board">
                <div>
                    <div id="favBtn" data-iboard="${param.iboard}" data-isfav="${detail.isFav}"></div>
                    <div>
                        조회수 : ${detail.hitCount}
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
                    <div id="updParentT">
                        <div id="title">${detail.title}</div>
                    </div>
                        <div class="userinfo">
                            <div>${detail.userNick}</div>
                            <div>${detail.regdt}</div>
                        </div>

                        <c:if test="${auth.iuser eq detail.iuser}">
                            <div>
                                <form id="boardMod" data-iboard="${param.iboard}" data-provider="${detail.provider}" data-title="${detail.title}" data-ctnt="${detail.ctnt}" onsubmit="return false;">
                                </form>
                            </div>
                        </c:if>
                    <div id="updParentC">
                        <div id="ctnt">${detail.ctnt}</div>
                    </div>
                    <c:forEach items="${img}" var="img">
                        <div>
                           <div> <img src="/pic/board/${detail.iboard}/${img.img}"></div>
                        </div>
                    </c:forEach>
                </div>
                <div class="cmt-box">
                    <form id="cmtFrm" onsubmit="return false;">
                        <textarea class="cmt-text" id="cmt" placeholder="댓글을 입력하세요 ※위치 맞춰야함"></textarea>
                        <input type="button" class="cmt-btn" value="댓글달기" onclick="insCmt();">
                    </form>
                </div>
                <div id="cmtList" data-iboard="${param.iboard}" data-iuser="${auth.iuser}"></div>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>