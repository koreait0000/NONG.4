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
                <form action="monthFarmTech">
                    <div class="clear bbsSection">
                        <div class="schBox">
                            <div class="outerRound">
                                <div class="innerRound">
                                    <div style="font-size: 30px; margin-bottom: 10px; ">이달의 농업 기술</div>
                                    <span class="fl">
                                        <strong class="nTitle">기간설정</strong>
                                        <span class="inputW">
                                            <select id="sEraInfo" name="sEraInfo">
                                                <option value>전체</option>
                                                <option value="01" <c:if test="${farmTechDomain.SEraInfo == '01'}">selected='selected'</c:if>>01월</option>
                                                <option value="02" <c:if test="${farmTechDomain.SEraInfo == '02'}">selected='selected'</c:if>>02월</option>
                                                <option value="03" <c:if test="${farmTechDomain.SEraInfo == '03'}">selected='selected'</c:if>>03월</option>
                                                <option value="04" <c:if test="${farmTechDomain.SEraInfo == '04'}">selected='selected'</c:if>>04월</option>
                                                <option value="05" <c:if test="${farmTechDomain.SEraInfo == '05'}">selected='selected'</c:if>>05월</option>
                                                <option value="06" <c:if test="${farmTechDomain.SEraInfo == '06'}">selected='selected'</c:if>>06월</option>
                                                <option value="07" <c:if test="${farmTechDomain.SEraInfo == '07'}">selected='selected'</c:if>>07월</option>
                                                <option value="08" <c:if test="${farmTechDomain.SEraInfo == '08'}">selected='selected'</c:if>>08월</option>
                                                <option value="09" <c:if test="${farmTechDomain.SEraInfo == '09'}">selected='selected'</c:if>>09월</option>
                                                <option value="10" <c:if test="${farmTechDomain.SEraInfo == '10'}">selected='selected'</c:if>>10월</option>
                                                <option value="11" <c:if test="${farmTechDomain.SEraInfo == '11'}">selected='selected'</c:if>>11월</option>
                                                <option value="12" <c:if test="${farmTechDomain.SEraInfo == '12'}">selected='selected'</c:if>>12월</option>
                                            </select>
                                        </span>
                                        <strong class="nTitle">제목/키워드</strong>
                                        <span class="inputW">
                                            <input type="text" id="srchStr" name="srchStr" size="50" value="${farmTechDomain.srchStr}">
                                        </span>
                                    </span>
                                    <span class="fr">
                                        <strong class="button medium">
                                            <input type="submit" value="검색">
                                        </strong>
                                    </span>
                                    <div class="cLear"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                <table>
                    <tr>
                        <th style="width: 5%;">번호</th>
                        <th>이미지</th>
                        <th style="width: 20%;">제목</th>
                        <th>요약내용</th>
                        <th style="width: 10%;">등록일</th>
                        <th style="width: 10%;">조회수</th>
                    </tr>
                    <c:forEach items="${farmTechDomain.farmTechItemList}" var="farmList" varStatus="status">
                        <tr class="bodyTr pointer" onclick="location.href='monthFarmTechDtl?srchCurationNo=' + ${farmList.curationNo}">
                            <td>${farmTechDomain.totalCount - (farmTechDomain.pageNo - 1) * 10 - status.index}</td>
                            <td><img src="${img[status.index]}" style="width: 120px; height: 80px; padding: 10px;"></td>
                            <td style="padding: 10px;">${farmList.curationNm}</td>
                            <td class="sumryDtl">${farmList.curationSumryDtl}</td>
                            <td style="padding-left: 5px;">${farmList.svcDt}</td>
                            <td>${farmList.rdCnt}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="pageMaker">
                    <c:if test="${pageMaker.prev}">
                        <a href="monthFarmTech?pageNo=${pageMaker.startPage - 1}&page=${pageMaker.startPage - 1}&sEraInfo=${farmTechDomain.SEraInfo}&srchStr=${farmTechDomain.srchStr}">이전</a>
                    </c:if>
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                        <a href="monthFarmTech?pageNo=${pageNum}&page=${pageNum}&sEraInfo=${farmTechDomain.SEraInfo}&srchStr=${farmTechDomain.srchStr}">${pageNum}</a>
                    </c:forEach>
                    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                        <a href="monthFarmTech?pageNo=${pageMaker.endPage + 1}&page=${pageMaker.endPage + 1}&sEraInfo=${farmTechDomain.SEraInfo}&srchStr=${farmTechDomain.srchStr}">다음</a>
                    </c:if>
                </div>
            </form>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>