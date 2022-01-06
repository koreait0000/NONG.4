<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                <div class="title">알림판<i class="fas fa-list"></i></div>
                <div><a href="">공지사항</a></div>
                <div><a href="/notice/market">산들장터</a></div>
            </div>

            <div class="cate-board">
                <div class="title">커뮤니티 <i class="fas fa-list"></i></div>
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
                <table>
                    <tr>
                        <th>번호</th>
                        <th>이미지</th>
                        <th>제목</th>
                        <th>요약내용</th>
                        <th>등록일</th>
                    </tr>
                    <c:forEach items="${farmTechDomain.farmTechItemList}" var="farmList">
                        <tr class="bodyTr pointer" onclick="location.href='monthFarmTechDtl?srchCurationNo=' + ${farmList.curationNo}">
                            <th>${farmTechDomain.totalCount}</th>
                            <td>${farmList.thumbFileNm}</td>
                            <td>${farmList.curationNm}</td>
                            <td>${farmList.curationSumryDtl}</td>
                            <td>${farmList.svcDt}</td>
                        </tr>
                    </c:forEach>
                </table>
                <form action="monthFarmTech">
                    <div class="pageMaker">
                        <c:if test="${pageMaker.prev}">
                            <a href="monthFarmTech?pageNo=${pageMaker.startPage - 1}&page=${pageMaker.startPage - 1}">이전</a>
                        </c:if>
                        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                            <a href="monthFarmTech?pageNo=${pageNum}&page=${pageNum}"}>${pageNum}</a>
                        </c:forEach>
                        <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                            <a href="monthFarmTech?pageNo=${pageMaker.endPage + 1}&page=${pageMaker.endPage + 1}">다음</a>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>