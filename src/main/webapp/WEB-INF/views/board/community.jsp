<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <div class="community">
            <div class="community-top"></div>
            <div class="community-board">
                <div class="level">
                    <div class="swiper-container">
                        <!-- Additional required wrapper -->
                        <div class="swiper-wrapper">
                            <!-- Slides -->
                            <div class="swiper-slide"><img src="/res/img/junior.png" class="junior-class pointer"></div>
                            <div class="swiper-slide"><img src="/res/img/intermediate.png" class="intermediate-class pointer"></div>
                            <div class="swiper-slide"><img src="/res/img/advanced.png" class="advanced-class pointer"></div>
                            <div class="swiper-slide"><img src="/res/img/master.png" class="master-class pointer"></div>
                        </div>
                        <!-- If we need pagination -->
                        <div class="swiper-pagination"></div>
                        <!-- If we need navigation buttons -->
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
                <div class="news">
                    <div>주요소식</div>
                    <c:forEach begin="1" end="5">
                        <div>[질문게시판] 이러쿵저러쿵 여러 질문들이 있다 ※나중에 테이블 만들어서 수정</div>
                    </c:forEach>
                </div>
                <div class="today-video">
                    <div class="video-box"><img src="/res/img/main.png"></div>
                    <div class="video-cnt">
                        <div>오늘의 영상</div>
                        <div>오늘의 영상에 대한 약간의 설명? 동영상 크기 더 키워야함</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>
<script type="module">
    const swiper = new Swiper('.swiper-container', {
        // Optional parameters
        slidesPerView : 2, // 동시에 보여줄 슬라이드 갯수
        spaceBetween : -60, // 슬라이드간 간격
        direction: 'horizontal',
        loop: true,
        autoplay : {  // 자동 슬라이드 설정 , 비 활성화 시 false
            delay : 3000,   // 시간 설정
            disableOnInteraction : false,  // false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
        },
        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
        },
        // Navigation arrows
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });
</script>

