<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="category">
            <a href="/board/community"><img src="/res/img/nong4logo.png">
                <div style="font-size: 50px; margin-bottom: 8px; font-weight: bold"> Nong 4</div></a>

            <div class="search-input">
                <form action="totalSearch">
                    <input type="text" class="search-txt" name="title">
                    <i class="fas fa-search pointer" onclick="location.href='totalSearch'"></i>
                </form>
            </div>

            <div>
                <button class="writeBtn pointer" onclick="location.href='boardWrite'">
                    <i class="fas fa-pen">글쓰기</i>
                </button>
            </div>

            <div class="cate-event">
                <div class="title">알림판 <i class="fas fa-list"></i></div>
                <div><a href="">공지사항</a></div>
                <div><a href="/notice/market">산들장터</a></div>
            </div>

            <div class="cate-info">
                <div class="title">자료실 <i class="fas fa-list"></i></div>
                <div><a href="/api/apiVideo">주제별 동영상</a></div>
                <div><a href="/api/monthFarmTech">농업기술</a></div>
                <div><a href="/api/farmWorkingPlan">농작업일정</a></div>
            </div>

            <div class="cate-board">
                <div class="title">게시판 <i class="fas fa-list"></i></div>
                <div><a href="mainBoard?provider=">통합 게시판</a></div>
                <div><a href="mainBoard?provider=freedom">자유 게시판</a></div>
                <div><a href="mainBoard?provider=question">질문 게시판</a></div>
                <div><a href="mainBoard?provider=strategy">공략 게시판</a></div>
                <div><a href="mainBoard?provider=friend">친구 게시판</a></div>
            </div>
        </div>
        <div class="community">
            <div class="community-top"></div>
            <div class="community-board">
                <div class="list-board">
                    <div class="boardlist-bottom" data-pagenum="${pageMaker.cri.page}" data-keyword="${pageMaker.keyword}" >
                    </div>
                </div>
                <div class="list-video" ></div>
                <div class="list-month">
                    @@ : ${farmTech}
                </div>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>