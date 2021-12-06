<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="join-page">
    <form class="joinForm" id="joinForm" method="post" onsubmit="return false" >
    <div class="join-email">
        <input type="email" name="email" id="email" placeholder="email" autofocus required>
        <div id="msg">
            <p> <strong>이메일</strong>을 입력해주세요 </p>
        </div>
            <button id="idCheck" type="button">중복확인</button>
    </div>
    <div class="join-pw">
        <input type="password" name="pw" id="pw" placeholder="password" required>
        <p> <strong>비밀번호</strong> 를 입력해주세요 </p>
    </div>
    <div class="join-pw">
        <input type="password" name="pwRe" id="pwRe" placeholder="password" required>
        <p> <strong>비밀번호</strong> 를 한번 더 입력해주세요 </p>
    </div>
    <div class="join-nm">
        <input type="text" name="nm" id="nm" placeholder="회원이름" required>
        <div id="msgNm">
            <p> <strong>이름</strong>을 입력해주세요 </p>
        </div>
    </div>
    <div class="join-nick">
    <h1>기본정보입력</h1>
        <input type="text" name="userNick" id="userNick" placeholder="닉네임" required>
        <div id="msgUserNick">
            <p> <strong>별명</strong>을 입력해주세요 </p>
        </div>
    </div>
        <div class="join-tel"><input type="tel" id="tel" name="tel" maxlength="11" placeholder="휴대폰번호" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');" required>
            <div id="msgTel">
                <p> <strong>전화번호</strong>를 입력해주세요 </p>
            </div>
        </div>
        <div class="join-submit-reset">
            <div class="join-submit"><input id="joinBtn" type="button" value="회원가입" disabled="disabled"></div>
            <div class="join-reset"><input class="pointer" type="reset" value="새로입력"></div>
        </div>
    </form>
</div>
