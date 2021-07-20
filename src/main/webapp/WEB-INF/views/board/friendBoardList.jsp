<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
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
            <c:forEach items="${data}" var="data">
                <tr class="record">
                    <td>${data.iboard}</td>
                    <td>${data.title}</td>
                    <td>${data.ctnt}</td>
                    <td>${data.userNick}</td>
                    <td>${data.regdt}</td>
                    <td>${data.provider}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>