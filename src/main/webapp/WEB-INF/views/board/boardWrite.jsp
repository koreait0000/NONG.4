<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="category">
            <a href="/board/community"><img src="/res/img/nong4logo.png">
                <div style="font-size: 50px; margin-bottom: 8px; font-weight: bold"> Nong 4</div></a>

            <div class="search-input">
                <input type="text" class="search-txt">
                <i class="fas fa-search pointer"></i>
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
                <div class="write-parents">
                    <form action="boardWrite" method="post" enctype="multipart/form-data">
                        <label for="provider"></label>
                        <select id="provider" name="provider" size="1">
                            <option value="freedom">자유게시판</option>
                            <option value="question">질문게시판</option>
                            <option value="strategy">공략게시판</option>
                            <option value="friend">친구게시판</option>
                        </select>
                        <div>
                            <input type="text" name="title" placeholder="제목을 작성해주세요" class="write-title">
                        </div>
                        <div>
                            <textarea name="ctnt" placeholder="궁금한 내용을 질문해 주세요" class="write-ctnt"></textarea>
                        </div>
                        <div class="write-file">
                            <input type="file" name="imgArr" multiple accept="image/*">
                        </div>
                        <div class="write-btn">
                            <input type="submit" value="글쓰기">
                            <input type="reset" value="초기화">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="background-right"></div>
</div>
</body>
</html>