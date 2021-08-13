<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.user" var="auth"></sec:authentication>

<div class="serviceContainer">
    <div class="serviceBox">
        <div class="service_before">
            <div>로고 넣을 부분</div>
            <h1>서비스  센터</h1>
            <div>불편하신 점을 <br> 문의를 통해 접수해 주세요! <br> 상담시간 <br> Open 09:00 - 18:00</div>
            <button class="serviceBtn">문의하기</button>
        </div>
        <div class="service_after hide">
            <div>로고 넣을 부분</div>
            <h1>문의가 접수되었습니다!</h1>
            <button class="moreServiceBtn">다른 문의하기</button>
        </div>
        <div class="service_modal hide">
            <div style="background-color: green;">
                <div class="modal_close_parent"><i class="modal_close fas fa-times"></i></div>
                <div><input type="text" class="serviceTitle" placeholder="문의 제목"></div>
                <div><textarea class="serviceCtnt" placeholder="문의 내용"></textarea></div>
                <button class="sendBtn">문의 보내기</button>
            </div>
        </div>
    </div>
</div>