<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="friendBoard" method="post">
        <label for="provider"></label>
        <select id="provider" name="provider" size="1">
            <option value="freedom">자유게시판</option>
            <option value="question">질문게시판</option>
            <option value="strategy">공략게시판</option>
            <option value="friend">친구게시판</option>
        </select>
        <div>
            <input type="text" name="title" placeholder="제목">
        </div>
        <div>
            <textarea name="ctnt" placeholder="내용" ></textarea>
        </div>
        <div>
            <input type="submit" value="글쓰기">
            <input type="reset" value="초기화">
        </div>
    </form>
</div>
</body>
</html>