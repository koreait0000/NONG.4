<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <c:forEach items="${reqDomain.videoItemList}" var="aa">
        pageNo : ${aa.videoImg}

    </c:forEach>
    ${reqDomain.pageNo}
</div>