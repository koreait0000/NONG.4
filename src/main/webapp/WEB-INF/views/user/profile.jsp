<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.user" var="auth"></sec:authentication>
auth : ${auth.iuser}
profile : ${profile.iuser}
<c:if test="${auth.iuser eq profile.iuser}">
    <form id="profileForm" action="profile" method="post">
        <div>
            이미지 : <input type="file" name="imgArr" multiple accept="image/*">
            <input type="submit" value="업로드">
        </div>
    </form>
    <div id="flexContainer">
        <table>
            <img id="profile" src="/pic/profile/${profile.iuser}/${profile.profileImg}" onerror="this.onerror=null; this.style.display='none';">
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