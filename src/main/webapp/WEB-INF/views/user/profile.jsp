<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.user" var="auth"></sec:authentication>
<c:if test="${auth.iuser eq profile.iuser}">

    <div class="pointer profileMod">프로필 수정</div>
        <div class="modal-img hide">
            <div>
                <div class="container">
                    <div class="profileCont"></div>
                </div>
            </div>
        </div>

    <div class="modal hide">
        <div class="modal_close_parent"><i class="modal_close fas fa-times"></i></div>
        <div style="background-color: white;"></div>


    <div id="displayImgList" data-usernick="${profile.userNick}" data-iuser="${profile.iuser}" data-profile="${profile.profileImg}" data-provider="${profile.provider}">
        <h2>SanDeul</h2>
        <p> 커뮤니티에 사용하실 닉네임을 설정해 주세요 ! </p>
        <div id="displayImg"></div>
        <div id="btnCont"></div>
    </div>
    </div>

    <div id="flexContainer">
        <table>
            <img class="profileImg wh100" src="/pic/profileImg/${profile.iuser}/${profile.profileImg}" onerror="this.onerror=null; this.style.display='none';">
            <tr>
                <th>이메일</th>
                <td>${profile.email}</td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td>
                    <button class="pwChange pointer">비밀번호 변경</button>
                    <div class="pwModal-img hide">
                        <div>
                            <div class="container">
                                <div class="pwChangeCont"></div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td>${profile.userNick}</td>
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

    <div id="FavBoardList">
        <div>내가 좋아요한 글 리스트</div>
            <table>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일자</th>
                    <th>게시판성격</th>
                </tr>

                <c:forEach items="${list}" var="list" >
                    <c:if test="${list.isFav == 1}">
                        <tr class="record pointer" onclick="moveToDetail(${list.iboard});">
                            <td>${list.iboard}</td>
                            <td>${list.title}</td>
                            <td>${list.userNick}</td>
                            <td>${list.regdt}</td>
                            <td>${list.provider}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <div class="pagemaker">
                <c:if test="${pageMaker.prev}">
                    <a href="profile?page=${pageMaker.startPage - 1}">이전</a>
                </c:if>
                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                    <a href="profile?page=${pageNum}">${pageNum}</a>
                </c:forEach>
                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                    <a href="profile?page=${pageMaker.endPage + 1}">다음</a>
                </c:if>
            </div>
    </div>

    <div class="modal hide">

    </div>
</c:if>