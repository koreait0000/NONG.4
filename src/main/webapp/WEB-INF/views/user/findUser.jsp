<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="container">
    <form id="frm">
        <h3>Find Email / Pw</h3>
    </form>
    <div>
        <label class="search_label" for="search_1"> <input
                type="radio" id="search_1" name="search_total"
                onclick="search_check(1)" checked="checked"><span>Find
						ID</span>
        </label> <label class="search_label" for="search_2"> <input
            type="radio" id="search_2" name="search_total"
            onclick="search_check(2)"><span>Find PW</span>
    </label>
    </div>
    <p>* 인증된 메일만 찾기 가능 *</p>
    <!-- 아이디 찾기 폼 -->
    <div id="searchId" class="search-input">
        <div>
            <input type="text" id="nm" name="nm" placeholder="이름"
                   autocomplete="off" />
        </div>
        <div>
            <input type="text" id="tel" name="tel" placeholder="휴대폰번호"
                   autocomplete="off" />
        </div>

        <div id="btn1">
            <button id="searchBtn1" type="button" onclick="EmailFind_click()">확인</button>
            <a href="${pageContext.request.contextPath}"><button
                    type="button">취소</button></a>
        </div>
        <div id="msg"></div>
    </div>

    <!-- 비밀번호 찾기 폼 -->
    <div id="searchPw" class="search-input" style="display: none">
        <div>
            <input type="email" id="email" name="u_mail" placeholder="이메일" />
        </div>
        <div id="btn">
            <button id="searchBtn2" type="button" onclick="pwSearch_click()">확인</button>
            <a href="${pageContext.request.contextPath}"><button
                    type="button">취소</button></a>
        </div>
    </div>
    <div class="login-btn">
        <a href="/user/login">Login</a>
    </div>

</div>