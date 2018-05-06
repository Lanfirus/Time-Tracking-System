<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ include file="jspParts/general.jsp" %>
<%@ include file="jspParts/language-setup.jsp"%>
<%@ include file="jspParts/header-empty.jsp"%>

<link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/reg-form.css" >

<div class="main">
    <h2><fmt:message key="doublelogin.initialMessage" /></h2>
    <br>
           <a href="${pageContext.request.contextPath}/tts/main"><fmt:message key="error.link.mainPage" /></a>
</div>

<jsp:include page="jspParts/footer.jsp"/>