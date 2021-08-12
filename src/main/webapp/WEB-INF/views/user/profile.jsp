<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.user" var="auth"></sec:authentication>
auth : ${auth.iuser}
profile : ${profile.iuser}
<c:if test="${auth.iuser eq profile.iuser}">
    <div class="modal-img">
        <div>
            <div class="container">
                <div class="top">
                    <div id="title">프로필 수정</div>
                    <i id="modal-img-close" class="modal_close fas fa-times"></i>
                </div>
                <div class="followCont"></div>
            </div>
        </div>
    </div>

    <div class="modal hide">
        <div class="modal_close_parent"><i class="modal_close fas fa-times"></i></div>
        <div style="background-color: white;"></div>
    </div>

    <div>
        이미지 : <input type="file" id="selectImgArr" accept="image/*">
        <input type="submit" id="submitUpload" value="업로드">
    </div>
    <div id="displayImgList"></div>
    <div id="flexContainer">
        <table>
            <img class="profileImg wh100" src="/pic/profileImg/${profile.iuser}/${profile.profileImg}" onerror="this.onerror=null; this.style.display='none';">
            <tr>
                <th>이메일</th>
                <td>${profile.email}</td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td>${profile.nick}</td>
            </tr>
            <tr>
                <th>휴대폰번호</th>
                <td>${profile.tel}</td>
            </tr>
            <tr>
                <th>가입일자!</th>
                <td>${profile.regdt}</td>
            </tr>
        </table>
    </div>
</c:if>