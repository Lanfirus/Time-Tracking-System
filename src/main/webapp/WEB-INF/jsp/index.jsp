<%@ include file="jspParts/general.jsp"%>
<%@ include file="jspParts/language-setup.jsp"%>
<%@ include file="jspParts/header-guest.jsp"%>

<section class="land-panorama">

    <div class="main-container">
        <h1 class="land-panorama-h1">Time Tracking<br>&amp; Scope Management</h1>
            <p class="land-panorama-description">Software to boost business with intelligent data</p>
            <a class="button button-land" href="${pageContext.request.contextPath}/tts/login_form">Start Using XTIME</a>
    </div>
</section>

   <section class="land-body">
          <div class="main-container">
              <h2 class="land-body-h2 land-body-h2-long">Create project scope,  assign work to your team,
              record work hours and keep everything on track with the insightful data</h2>
          </div>
   </section>

   <c:if test="${not empty requestScope.badLoginPassword}">
           <script>
           alert("<fmt:message key="registration.bad.loginPassword" />");
           </script>
   </c:if>

<jsp:include page="jspParts/footer.jsp"/>
