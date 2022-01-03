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
                <div><a href="/board/mainBoard">통합 게시판</a></div>
                <div><a href="/board/mainBoard?provider=freedom">자유 게시판</a></div>
                <div><a href="/board/mainBoard?provider=question">질문 게시판</a></div>
                <div><a href="/board/mainBoard?provider=strategy">공략 게시판</a></div>
                <div><a href="/board/mainBoard?provider=friend">친구 게시판</a></div>
            </div>
        </div>

        <div class="community">
            <div class="community-top"></div>
            <div class="community-board">
                <form action="junior">
                    <input type="hidden" name="pageNo" value="${reqDomain.pageNo}">
                    <input type="hidden" name="totalCount" value="${reqDomain.totalCount}">
                    <div id="videoSearch">
                        <div class="outerRound">
                            <div class="innerRound">
                                <div class="innerInput">
                                    <select class="sType" id="sType" name="sType">
                                        <option value="sSj" selected>주제목</option>
                                        <option value="sMvpClipSj">짧은 기술동영상 제목</option>
                                    </select>
                                    <input class="sText" id="sText" type="text" name="sText">
                                </div>
                                <div class="innerSubmit">
                                    <strong class="button">
                                        <input type="submit" value="조회">
                                    </strong>
                                </div>
                            </div>
                            <div class="innerRound">
                                <div class="innerCategory">
                                    <strong class="nTitle">품목 분류</strong>
                                    <select class="mainCategory" id="mainCategory" name="mainCategory">
                                        <option value="DF" selected>선택하세요</option>
                                        <option value="FC">식량작물</option>
                                        <option value="IC">특용작물</option>
                                        <option value="VC">채소</option>
                                        <option value="FT">과수</option>
                                        <option value="FL">화훼</option>
                                        <option value="LP">축산</option>
                                        <option value="IN">곤충</option>
                                        <option value="AE">농업공학</option>
                                        <option value="RE">농촌환경</option>
                                        <option value="EE">환경생태</option>
                                        <option value="SF">토양비료</option>
                                        <option value="CS">농산물안정성</option>
                                        <option value="MI">농업경영 · 정보</option>
                                        <option value="FR">농식품자원</option>
                                        <option value="AS">농업재해예방</option>
                                        <option value="CA">도시농업</option>
                                        <option value="BT">생명공학</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                <table>
                    <tr>
                        <th>동영상</th>
                        <th>품목분류</th>
                        <th>주제목</th>
                        <th>짧은 기술동영상 제목</th>
                    </tr>
                    <c:forEach items="${reqDomain.videoItemList}" var="videoList">
                        <tr class="bodyTr pointer" onclick="location.href='${videoList.videoLink}'">
                            <th><img src=${videoList.videoImg}></th>
                            <th>${videoList.stdPrdlstCodeNm}</th>
                            <th>${videoList.sj}</th>
                            <th>${videoList.mvpClipSj}</th>
                        </tr>
                    </c:forEach>
                </table>
                <div class="pagemaker">
                    <c:if test="${pageMaker.prev}">
                        <a href="mainBoard?provider=${param.provider}&page=${pageMaker.startPage - 1}&searchType=${param.searchType == null ? 'n' : param.searchType}&keyword=${param.keyword == null ? '' : param.keyword}">이전</a>
                    </c:if>
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                        <a href="mainBoard?provider=${param.provider}&page=${pageNum}&searchType=${param.searchType == null ? 'n' : param.searchType}&keyword=${param.keyword == null ? '' : param.keyword}">${pageNum}</a>
                    </c:forEach>
                    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                        <a href="mainBoard?provider=${param.provider}&page=${pageMaker.endPage + 1}&searchType=${param.searchType == null ? 'n' : param.searchType}&keyword=${param.keyword == null ? '' : param.keyword}">다음</a>
                    </c:if>
                </div>
                </form>
            </div>
        </div>
    </div>

    <div class="background-right"></div>
</div>