<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <c:forEach items="${list}" var="list">
                <tr class="record">
                    <td>${list.iboard}</td>
                    <td>${list.title}</td>
                    <td>${list.ctnt}</td>
                    <td>${list.userNick}</td>
                    <td>${list.regdt}</td>
                    <td>${list.provider}</td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${pageMaker.prev}">
            <a href="friendBoardList?provider=${param.provider}&page=${pageMaker.startPage-1}">이전</a>
        </c:if>
        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
            <a href="friendBoardList?provider=${param.provider}&page=${pageNum}">${pageNum}</a>
        </c:forEach>
        <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
            <a href="friendBoardList?provider=${param.provider}&page=${pageMaker.endPage+1}">다음</a>
        </c:if>
    </form>
</div>