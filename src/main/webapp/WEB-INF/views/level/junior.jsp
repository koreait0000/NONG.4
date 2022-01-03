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
            <div class="community-board" id="community-board">
                <form action="junior">
                    <div id="videoSearch">
                        <div class="outerRound">
                            <div class="innerRound">
                                <div class="innerInput">
                                    <select class="sType" id="sType" name="sType">
                                        <option value="sSj" <c:if test="${reqDomain.SType == 'sSj'}">selected='selected'</c:if>>주제목</option>
                                        <option value="sMvpClipSj" <c:if test="${reqDomain.SType == 'sMvpClipSj'}">selected='selected'</c:if>>짧은 기술동영상 제목</option>
                                    </select>
                                    <input class="sText" id="sText" type="text" name="sText" value="${reqDomain.SText}">
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
                                        <option value="" selected>선택하세요</option>
                                        <option value="FC" <c:if test="${reqDomain.mainCategory == 'FC'}">selected='selected'</c:if>>식량작물</option>
                                        <option value="IC" <c:if test="${reqDomain.mainCategory == 'IC'}">selected='selected'</c:if> >특용작물</option>
                                        <option value="VC" <c:if test="${reqDomain.mainCategory == 'VC'}">selected='selected'</c:if>>채소</option>
                                        <option value="FT" <c:if test="${reqDomain.mainCategory == 'FT'}">selected='selected'</c:if>>과수</option>
                                        <option value="FL" <c:if test="${reqDomain.mainCategory == 'FL'}">selected='selected'</c:if>>화훼</option>
                                        <option value="LP" <c:if test="${reqDomain.mainCategory == 'LP'}">selected='selected'</c:if>>축산</option>
                                        <option value="IN" <c:if test="${reqDomain.mainCategory == 'IN'}">selected='selected'</c:if>>곤충</option>
                                        <option value="AE" <c:if test="${reqDomain.mainCategory == 'AE'}">selected='selected'</c:if>>농업공학</option>
                                        <option value="RE" <c:if test="${reqDomain.mainCategory == 'RE'}">selected='selected'</c:if>>농촌환경</option>
                                        <option value="EE" <c:if test="${reqDomain.mainCategory == 'EE'}">selected='selected'</c:if>>환경생태</option>
                                        <option value="SF" <c:if test="${reqDomain.mainCategory == 'SF'}">selected='selected'</c:if>>토양비료</option>
                                        <option value="CS" <c:if test="${reqDomain.mainCategory == 'CS'}">selected='selected'</c:if>>농산물안정성</option>
                                        <option value="MI" <c:if test="${reqDomain.mainCategory == 'MI'}">selected='selected'</c:if>>농업경영 · 정보</option>
                                        <option value="FR" <c:if test="${reqDomain.mainCategory == 'FR'}">selected='selected'</c:if>>농식품자원</option>
                                        <option value="AS" <c:if test="${reqDomain.mainCategory == 'AS'}">selected='selected'</c:if>>농업재해예방</option>
                                        <option value="CA" <c:if test="${reqDomain.mainCategory == 'CA'}">selected='selected'</c:if>>도시농업</option>
                                        <option value="BT" <c:if test="${reqDomain.mainCategory == 'BT'}">selected='selected'</c:if>>생명공학</option>
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
                        <tr class="bodyTr pointer" onclick="iframeFunc('${videoList.videoLink}');">
                            <th><img src=${videoList.videoImg}></th>
                            <th>${videoList.stdPrdlstCodeNm}</th>
                            <th>${videoList.sj}</th>
                            <th>${videoList.mvpClipSj}</th>
                        </tr>
                    </c:forEach>
                </table>
                <div class="pagemaker">
                    <c:if test="${pageMaker.prev}">
                        <a href="junior?pageNo=${pageMaker.startPage - 1}&page=${pageMaker.startPage - 1}&mainCategory=${reqDomain.mainCategory}&sType=${reqDomain.SType}&sText=${reqDomain.SText}">이전</a>
                    </c:if>
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                        <a href="junior?pageNo=${pageNum}&mainCategory=${reqDomain.mainCategory}&page=${pageNum}&sType=${reqDomain.SType}&sText=${reqDomain.SText}"}>${pageNum}</a>
                    </c:forEach>
                    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                        <a href="junior?pageNo=${pageMaker.endPage + 1}&page=${pageMaker.endPage + 1}&mainCategory=${reqDomain.mainCategory}&sType=${reqDomain.SType}&sText=${reqDomain.SText}">다음</a>
                    </c:if>
                </div>
                </form>
            </div>
        </div>
    </div>

    <div class="background-right"></div>
</div>