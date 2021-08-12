<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="join-page">
    <h1>기본정보입력</h1>
    <form class="joinForm" action="join" method="post">
        <div class="join-email">
            <input type="email" name="email" placeholder="email" autofocus required>
            <p> <strong>이메일</strong>을 입력해주세요 </p>
        </div>
        <div class="join-pw">
            <input type="password" name="pw" placeholder="password" required>
            <p> <strong>비밀번호</strong> 를 입력해주세요 </p>
        </div>
        <div class="join-nm">
            <input type="text" name="nm" placeholder="회원이름" required>
            <p> <strong>이름</strong>을 입력해주세요 </p>
        </div>
        <div class="join-nick"><input type="text" name="nick" placeholder="닉네임" required>
            <p> <strong>별명</strong>을 입력해주세요 </p>
        </div>
        <div class="join-tel"><input type="tel" name="tel" placeholder="휴대폰번호" required>
            <p> <strong>전화번호</strong>를 입력해주세요 </p>
        </div>
        <div class="join-submit-reset">
            <div class="join-submit"><input class="pointer" type="submit" value="회원가입"></div>
            <div class="join-reset"><input class="pointer" type="reset" value="새로입력"></div>
            <div>
    </form>
</div>
