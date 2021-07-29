<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<div class="background">
    <div class="background-left"></div>
    <div class="background-center">
        <div class="kategorie">
            <img src="/res/img/main.png">
            <div style="font-size: 50px; font-weight: bold"> Nong 4</div>
            <div><input type="text" class="searchtxt"><i class="fas fa-search pointer"></i></div>
            <div><a href="friendBoard"><button class="writeBtn pointer"><i class="fas fa-pen"></i>글쓰기</button></a></div>
            <div class="kategorieitem1">
                <div class="title">알림판 <i class="fas fa-list"></i></div>
                <div><a href="">공지사항</a></div>
                <div><a href="">이벤트</a></div>
            </div>
            <div class="kategorieitem2">
                <div class="title">커뮤니티 <i class="fas fa-list"></i></div>
                <div><a href="">통합 게시판</a></div>
                <div><a href="friendBoardList?provider=freedom">자유 게시판</a></div>
                <div><a href="friendBoardList?provider=question">질문 게시판</a></div>
                <div><a href="friendBoardList?provider=strategy">공략 게시판</a></div>
                <div><a href="friendBoardList?provider=friend">친구 게시판</a></div>
            </div>
        </div>
        <div class="community">
            <div class="community-top"></div>
            <div class="communityboard">
                <div>
                    <div>
                        ${param.provider == 'freedom'  ? '자유게시판' : ''}
                        ${param.provider == 'question' ? '질문게시판' : ''}
                        ${param.provider == 'strategy' ? '공략게시판' : ''}
                        ${param.provider == 'friend'   ? '친구게시판' : ''}
                    </div>
                    <form action="friendBoardList">
                        <table>
                            <tr>
                                <th>글번호</th>
                                <th>제목</th>
                                <th>내용</th>
                                <th>작성자</th>
                                <th>작성일자</th>
                                <th>게시판성격</th>
                            </tr>
                            <c:forEach items="${list}" var="list" >
                                <tr class="record pointer" onclick="moveToDetail(${list.iboard});">
                                    <td>${list.iboard}</td>
                                    <td>${list.title}</td>
                                    <td>${list.ctnt}</td>
                                    <td>${list.userNick}</td>
                                    <td>${list.regdt}</td>
                                    <td>${list.provider}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="pagemaker">
                            <c:if test="${pageMaker.prev}">
                                <a href="friendBoardList?provider=${param.provider}&page=${pageMaker.startPage - 1}&searchType=${param.searchType == null ? 'n' : param.searchType}&keyword=${param.keyword == null ? '' : param.keyword}">이전</a>
                            </c:if>
                            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                                <a href="friendBoardList?provider=${param.provider}&page=${pageNum}&searchType=${param.searchType == null ? 'n' : param.searchType}&keyword=${param.keyword == null ? '' : param.keyword}">${pageNum}</a>
                            </c:forEach>
                            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                <a href="friendBoardList?provider=${param.provider}&page=${pageMaker.endPage + 1}&searchType=${param.searchType == null ? 'n' : param.searchType}&keyword=${param.keyword == null ? '' : param.keyword}">다음</a>
                            </c:if>
                        </div>
                        <div class="search">
                          <input type="hidden" name="provider" value="${param.provider}">
                          <select name="searchType">
                              <option value="n"<c:out value=" ${scri.searchType == null ? 'selected' : ''}"/>>----</option>
                              <option value="t"<c:out value=" ${scri.searchType eq 't'  ? 'selected' : ''}"/>>제목</option>
                              <option value="c"<c:out value=" ${scri.searchType eq 'c'  ? 'selected' : ''}"/>>내용</option>
                              <option value="w"<c:out value=" ${scri.searchType eq 'w'  ? 'selected' : ''}"/>>작성자</option>
                              <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
                          </select>
                          <input type="text" name="keyword" id="keywordInput" value="${scri.keyword}">
                          <input type="submit" value="검색">
                          </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
   <div class="background-right"></div>
</div>