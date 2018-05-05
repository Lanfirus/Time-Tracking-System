<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="jspParts/general.jsp" %>
<%@ include file="jspParts/language-setup.jsp"%>
<%@ include file="jspParts/header-guest.jsp"%>

<link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/home-page.scss" >

<section class="land-panorama">

    <div class="main-container main-container--home-page">
        <h1 class="land-panorama__h1">Time Tracking<br>&amp; Scope Management</h1>
            <p class="land-panorama__description">Software to boost business with intelligent data</p>
            <a class="button button--land" href="${pageContext.request.contextPath}/tts/login_form">Start Using XTIME</a>
    </div>
</section>

   <section class="land-body land-body--slides">
          <div class="main-container main-container--home-page">
              <h2 class="land-body__h2 land-body__h2--long">Create project scope,  assign work to your team,
              record work hours and keep everything on track with the insightful data</h2>
          </div>
   </section>

   <c:if test="${not empty requestScope.badLoginPassword}">
           <script>
           alert("<fmt:message key="registration.bad.loginPassword" />");
           </script>
   </c:if>

<jsp:include page="jspParts/footer.jsp"/>
