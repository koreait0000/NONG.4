<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="category">
            <a href="/board/community"><img src="/res/img/nong4logo.png">
                <div style="font-size: 50px; margin-bottom: 8px; font-weight: bold"> Nong 4</div></a>

            <div class="search-input">
                <form action="totalSearch">
                    <input type="text" class="search-txt" name="keyword">
                    <input type="submit" class="fas fa-search pointer" value="&#xf002;">
<%--                    <i class="fas fa-search pointer" onclick="location.href='totalSearch?'+'1'"></i>--%>
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
                <div class="api">
                    <div class="swiper-container apiSwip">
                        <!-- Additional required wrapper -->
                        <div class="swiper-wrapper">
                            <!-- Slides -->
                            <div class="swiper-slide"><img src="/res/img/apivideo.jpg" class="apiVideo-class pointer"></div>
                            <div class="swiper-slide"><img src="/res/img/farmtech.jpg" class="monthFarmTech-class pointer"></div>
                            <div class="swiper-slide"><img src="/res/img/farmplan.jpg" class="farmWorkingPlan-class pointer">
                            </div>
                        </div>
                        <!-- If we need navigation buttons -->
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>

                <div class="news">
                    <div id="news-paging-btn" style="border-bottom: 1px solid #d3d3d3;">
                        주요소식
                    </div>
                    <div id="news-list"></div>
                </div>

                <div class="hotItem">
                    <div class="hotItemBox">
                        <div>산들장터 HOT 아이템(임시 이미지)</div>
                        <div class="swiper-container hotItemSwip">
                            <!-- Additional required wrapper -->
                            <div class="swiper-wrapper">
                                <!-- Slides -->
                                <div class="swiper-slide"><img src="/res/img/junior.png" class="apiVideo-class pointer"></div>
                                <div class="swiper-slide"><img src="/res/img/intermediate.png"
                                                               class="intermediate-class pointer"></div>
                                <div class="swiper-slide"><img src="/res/img/advanced.png" class="advanced-class pointer">
                                </div>
                                <div class="swiper-slide"><img src="/res/img/master.png" class="master-class pointer"></div>
                            </div>
                        </div>
                    </div>
                    <div class="hotItemMore"><a href="/notice/market"><i class="fas fa-chevron-right"></i>장터 둘러보기</a></div>
                </div>
            </div>
        </div>
    </div>

    <div class="background-right"></div>
</div>
