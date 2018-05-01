<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="jspParts/general.jsp" %>

<c:choose>

            <c:when test = "${sessionScope.role == 'employee'}">
                 <%@ include file="jspParts/header-employee.jsp"%>
            </c:when>

            <c:when test = "${sessionScope.role == 'admin'}">
                 <%@ include file="jspParts/header-admin.jsp"%>
            </c:when>

            <c:otherwise>
                <%@ include file="jspParts/header-empty.jsp"%>
            </c:otherwise>

        </c:choose>
<link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/reg-form.css" >
<div class="main">
<h2>
    <fmt:message key="registration.successful" />
</h2>
<form>
    <button class="submit" type="submit" onclick='this.form.action="login";'>
        <fmt:message key="login.login" />
    </button>
</form>
</div>
<jsp:include page="jspParts/footer.jsp"/>