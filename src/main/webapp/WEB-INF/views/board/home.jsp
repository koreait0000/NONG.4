<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%--<link rel="stylesheet" href="../../../res/css/board/home.css">--%>
<%--<script src="../../../res/js/board/home.js"></script>--%>
<head>
    <title>
        BoardList
    </title>
</head>
<body>
<h1>리스트</h1>
<div>
    <div>
        <img src="../../../res/img/main.png" class="main-img"/>
    </div>
    <div class="form-img">
        <div class="junior-class">
            <img src="../../../res/img/junior.png" id="junior-img"/>
        </div>

        <span class="intermediate-class">
            <img src="../../../res/img/intermediate.png"/>
        </span>

        <span class="advanced-class">
            <img src="../../../res/img/advanced.png"/>
        </span>

        <span class="master-class">
            <img src="../../../res/img/master.png"/>
        </span>
    </div>
</div>
</body>
</html>

<h1>메인홈 화면</h1>
<a href="community">게시판 커뮤니티</a>

<div class="swiper-container">
    <!-- Additional required wrapper -->
    <div class="swiper-wrapper">
        <!-- Slides -->
        <div class="swiper-slide">

            <div>
                <div class="profile-img-parent">
                    <img src="../../../res/img/junior.png">
                    <img src="../../../res/img/intermediate.png">
                    <img src="../../../res/img/advanced.png">
                    <img src="../../../res/img/master.png">
                </div>
            </div>
        </div>

    </div>
    <!-- If we need pagination -->
    <div class="swiper-pagination"></div>

    <!-- If we need navigation buttons -->
    <div class="swiper-button-prev"></div>
    <div class="swiper-button-next"></div>

    <!-- If we need scrollbar -->
    <div class="swiper-scrollbar"></div>
</div>

<script type="module">
    const swiper = new Swiper('.modal .swiper-container', {
        // Optional parameters
        direction: 'horizontal',
        loop: false,

        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
        },

        // Navigation arrows
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },

        // And if we need scrollbar
        scrollbar: {
            el: '.swiper-scrollbar',
        },
        effect: 'fade',
        fadeEffect: {
            crossFade: true
        },
    });
</script>