<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.user" var="auth"></sec:authentication>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="category">
            <a href="/board/community"><img src="/res/img/nong4logo.png">
            <div style="font-size: 50px; margin-bottom: 8px; font-weight: bold"> Nong 4</div></a>

            <div class="search-input">
                <input type="text" class="search-txt">
                <i class="fas fa-search pointer"></i>
            </div>

            <div>
                <button class="writeBtn pointer" onclick="location.href='/board/boardWrite'">
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
                <div><a href="/board/mainBoard?provider=">통합 게시판</a></div>
                <div><a href="/board/mainBoard?provider=freedom">자유 게시판</a></div>
                <div><a href="/board/mainBoard?provider=question">질문 게시판</a></div>
                <div><a href="/board/mainBoard?provider=strategy">공략 게시판</a></div>
                <div><a href="/board/mainBoard?provider=friend">친구 게시판</a></div>
            </div>
        </div>

        <div class="community">
            <div class="community-top"></div>
            <div class="community-board">
                <c:if test="${auth.iuser eq profile.iuser}">
                    <div class="pointer profileMod">
                        <button class="profileModBtn">프로필 수정</button>
                    </div>
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
                            <c:choose>
                                <c:when test="${empty profile.profileImg}">
                                    <img class="profileImg wh150" src="/res/img/BasicProfile.png" onerror="this.onerror=null; this.style.display='none';">
                                </c:when>
                                <c:otherwise>
                                    <img class="profileImg wh150" src="/pic/profileImg/${profile.iuser}/${profile.profileImg}" onerror="this.onerror=null; this.style.display='none';">
                                </c:otherwise>
                            </c:choose>
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
                                <th>가입일자</th>
                                <td>${profile.regdt}</td>
                            </tr>
                        </table>
                    </div>

                    <div id="FavBoardList">
                        <div>내가 좋아요한 글 리스트</div>
                        <table>
                            <tr>
                                <th style="width: 10%;">글번호</th>
                                <th style="width: 50%;">제목</th>
                                <th style="width: 10%;">작성자</th>
                                <th style="width: 20%;">작성일자</th>
                                <th style="width: 10%;">카테고리</th>
                            </tr>

                            <c:forEach items="${list}" var="list" >
                                <c:if test="${list.isFav == 1}">
                                    <tr class="record pointer" onclick="moveToDetail(${list.iboard});">
                                        <td>${list.iboard}</td>
                                        <td>${list.title}</td>
                                        <td>${list.userNick}</td>
                                        <td>${list.regdt}</td>
                                        <td>
                                            ${list.provider == 'freedom'  ? '자유게시판' : '' }
                                            ${list.provider == 'question' ? '질문게시판' : '' }
                                            ${list.provider == 'strategy' ? '공략게시판' : '' }
                                            ${list.provider == 'friend'   ? '친구게시판' : '' }
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>

                        <div class="pageMaker">
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
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>