<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="WEB-INF/jsp/jspParts/general.jsp" %>
<%@ include file="WEB-INF/jsp/jspParts/language-setup.jsp"%>

<c:choose>

            <c:when test = "${sessionScope.e_account_role == 'employee'}">
                 <%@ include file="WEB-INF/jsp/jspParts/header-employee.jsp"%>
                 <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/home-page.scss" >
                 <section class="land-panorama"></section>
            </c:when>

            <c:when test = "${sessionScope.e_account_role == 'admin'}">
                 <%@ include file="WEB-INF/jsp/jspParts/header-admin.jsp"%>
                 <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/home-page.scss" >
                 <section class="land-panorama"></section>
            </c:when>

            <c:otherwise>
                <%@ include file="WEB-INF/jsp/jspParts/header-guest.jsp"%>
                <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/home-page.scss" >
                <section class="land-panorama">
                    <div class="main-container main-container--home-page">
                        <h1 class="land-panorama__h1">Time Tracking<br>&amp; Scope Management</h1>
                           <p class="land-panorama__description">Software to boost business with intelligent data</p>
                           <a class="button button--land" href="${pageContext.request.contextPath}/tts/login_form">Start Using XTIME</a>
                    </div>
                </section>

            </c:otherwise>

        </c:choose>

   <section class="land-body land-body--slides">
          <div class="main-container main-container--home-page">
              <h2 class="land-body__h2 land-body__h2--long">Create project scope,  assign work to your team,
              record work hours and keep everything on track with the insightful data</h2>
          </div>
   </section>
<jsp:include page="WEB-INF/jsp/jspParts/footer.jsp"/>
