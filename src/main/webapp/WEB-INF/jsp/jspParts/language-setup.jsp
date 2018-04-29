<c:choose>

    <c:when test = "${not empty sessionScope.language}">
        <c:set var="language" value="${sessionScope.language}" />
    </c:when>

    <c:otherwise>
        <c:set var="language" value="en" scope="session"/>
    </c:otherwise>

</c:choose>