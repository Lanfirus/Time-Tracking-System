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
<form class="contact_form" action="registration" method="post" name="contact_form">

    <ul>
        <li>
        <c:choose>

            <c:when test = "${empty requestScope.userView}">
                 <h2><fmt:message key="registration.formName" /></h2>
                 <span class="required_notification"><fmt:message key="registration.mandatoryFields" /></span>
            </c:when>

            <c:otherwise>
                <h2><fmt:message key="view.title" /></h2>
                <span class="required_notification"><fmt:message key="registration.mandatoryFields" /></span>
            </c:otherwise>

        </c:choose>

        </li>

        <li>
            <label for="login"><fmt:message key="registration.login" />:</label>
            <input type="text" name="e_login" placeholder="<fmt:message key="registration.loginPlaceholder" />" required
                pattern="^(?!
                    <c:if test = "${not empty requestScope.loginProblem}">
                        ${requestScope.login}
                    </c:if>
                    .+"
                    value="${requestScope.login}"
            />
            <c:if test = "${not empty requestScope.loginProblem}">
                <br>
                <p style="color:red;">Warning: You have to choose another login!</p>
            </c:if>

            <span class="form_hint">
                <c:if test = "${not empty requestScope.loginProblem}">
                    <fmt:message key="registration.loginProblemRegexp" />
                </c:if>
                <fmt:message key="registration.loginRegexp" /></span>
        </li>

        <li>
            <label for="password"><fmt:message key="registration.password" />:</label>
            <input type="password" name="e_password" placeholder="<fmt:message key="registration.passwordPlaceholder" />"
                required pattern=".+"
            />
            <span class="form_hint"><fmt:message key="registration.passwordRegexp" /></span>
        </li>

        <li>
            <label for="name"><fmt:message key="registration.name" />:</label>
            <input type="text" name="e_name" placeholder="<fmt:message key="registration.namePlaceholder" />"
                required pattern="^(?!.*?([ `'\-]{2,}|[A-Z]{2,}|[A-Z `'\-]$))[A-Z]{1}[a-z\- '`A-Z]+$"
                <c:if test = "${not empty requestScope.name}">
                        value="${requestScope.name}"
                </c:if>
            />
            <span class="form_hint"><fmt:message key="registration.nameRegexp" /></span>
        </li>

        <li>
            <label for="surname"><fmt:message key="registration.surname" />:</label>
            <input type="text" name="e_surname" placeholder="<fmt:message key="registration.surnamePlaceholder" />"
                required pattern="^(?!.*?([ `'\-]{2,}|[A-Z]{2,}|[A-Z `'\-]$))[A-Z]{1}[a-z\- '`A-Z]+$"
                <c:if test = "${not empty requestScope.surname}">
                        value="${requestScope.surname}"
                </c:if>
            />
            <span class="form_hint"><fmt:message key="registration.surnameRegexp" /></span>
        </li>

        <li>
            <label for="patronymic"><fmt:message key="registration.patronymic" />:</label>
            <input type="text" name="e_patronymic" placeholder="<fmt:message key="registration.patronymicPlaceholder" />"
                pattern="^(?!.*?([ `'\-]{2,}|[A-Z]{2,}|[A-Z `'\-]$))[A-Z]{1}[a-z\- '`A-Z]+|$"
                <c:if test = "${not empty requestScope.patronymic}">
                        value="${requestScope.patronymic}"
                </c:if>
            />
            <span class="form_hint"><fmt:message key="registration.patronymicRegexp" /></span>
        </li>

        <li>
            <label for="email"><fmt:message key="registration.email" />:</label>
            <input type="text" name="e_email" placeholder="email@email.com" required
                pattern="^(?!.*?[._\-]{2,})[a-zA-Z0-9]{1}[a-zA-Z0-9._\-]{0,62}[a-zA-Z0-9]?@{1}(?!.{256,})([a-zA-Z0-9]{1}[a-zA-Z0-9._\-]{1,254}[a-zA-Z0-9]{1}|\[{1}\d{1,3}\.{1}\d{1,3}\.{1}\d{1,3}\.{1}\d{1,3}\]{1})$"
                <c:if test = "${not empty requestScope.email}">
                        value="${requestScope.email}"
                </c:if>
            />
            <span class="form_hint"><fmt:message key="registration.emailRegexp" /></span>
        </li>

        <li>
            <label for="mobilephonenumber"><fmt:message key="registration.mobilePhoneNumber" />:</label>
            <input type="text" name="e_mobile_phone" placeholder="380501234567"
                required pattern="^(?:380\d{9}|\d{10,12})$"
                <c:if test = "${not empty requestScope.mobilePhoneNumber}">
                        value="${requestScope.mobilePhoneNumber}"
                </c:if>
            />
            <span class="form_hint"><fmt:message key="registration.mobilePhoneNumberRegexp" /></span>
        </li>

        <li>
            <label for="comment"><fmt:message key="registration.comment" />:</label>
            <input type="text" name="e_comment" placeholder="<fmt:message key="registration.commentPlaceholder" />"
                pattern=".*"
                <c:if test = "${not empty requestScope.comment}">
                        value="${requestScope.comment}"
                </c:if>
            />
            <span class="form_hint"><fmt:message key="registration.commentRegexp" /></span>
        </li>

        <li>
            <c:choose>

                <c:when test = "${empty requestScope.userView}">
                    <button class="submit" type="submit"><fmt:message key="registration.submit" /></button>
                </c:when>

                <c:otherwise>
                    <button class="submit" type="submit" onclick='this.form.action="update";'>
                        <fmt:message key="registration.update" /></button>
                    <button class="submit" type="submit" onclick='this.form.action="delete";'>
                        <fmt:message key="registration.delete" /></button>
                </c:otherwise>

            </c:choose>


        </li>

    </ul>

</form>
       </div>

<jsp:include page="jspParts/footer.jsp"/>