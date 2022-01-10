<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div id="apiLoadingArea">
                    <div class="apiContArea">
                        <h3>
                            <p class="fl">
                                <strong>농작업일정</strong>
                            </p>
                        </h3>
                        <div class="back-gray">
                            <strong>품목별 관리매뉴얼 (농작업 관리일정)이란?</strong>
                            <p class="mt10">
                                ...
                            </p>
                            <div class="clear bbsSection">
                                <fieldset class="sch">
                                    <legend></legend>
                                    <div class="tabArea">
                                        <ul>
                                            <c:forEach items="${farmWorkingPlanDomain.workingItemList}" var="workingList" varStatus="status">
                                                <a id="${workingList.kidofcomdtySeCode}" href="#" onclick="fncViewButtom('${workingList.kidofcomdtySeCode}'); return false;">
                                                <li>${workingList.codeNm}</li>
                                                </a>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="apiLoadingAreaButtom">
                    <div class="apiContArea">
                        <div class="bbsSection">
                            <div class="tableRound">
                                <table class="grid tobLine" border="1" cellspacing="0" cellpadding="0">
                                    <caption>품목별 관리매뉴얼 목록</caption>
                                    <colgroup>
                                        <col width="33%">
                                        <col width="*">
                                        <col width="33%">
                                    </colgroup>
                                    <tbody class="tbody">
                                    <c:forEach items="${farmWorkingPlanDomain.workingScheduleList}" var="scheduleLst" varStatus="status">
                                        <tr>
                                            <td>
                                                <a href="#">${scheduleLst.sj}</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="background-right"></div>
</div>