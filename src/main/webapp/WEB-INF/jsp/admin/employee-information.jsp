<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-admin.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="main">
    <h2><fmt:message key="employeeInformation.title" /></h2>

    <table>
        <tr>
            <th><fmt:message key="employeeInformation.table.id"/></th>
            <th><fmt:message key="employeeInformation.table.login"/></th>
            <th><fmt:message key="employeeInformation.table.password"/></th>
            <th><fmt:message key="employeeInformation.table.name"/></th>
            <th><fmt:message key="employeeInformation.table.surname"/></th>
            <th><fmt:message key="employeeInformation.table.patronymic"/></th>
            <th><fmt:message key="employeeInformation.table.email"/></th>
            <th><fmt:message key="employeeInformation.table.mobilePhone"/></th>
            <th><fmt:message key="employeeInformation.table.comment"/></th>
            <th><fmt:message key="employeeInformation.table.accountRole"/></th>
            <th><fmt:message key="employeeInformation.table.changeRole"/></th>
            <th><fmt:message key="employeeInformation.table.delete"/></th>
        </tr>

        <c:forEach var="employee" items="${employees}">
            <tr>
                <td><c:out value="${employee.id}"/></td>
                <td><c:out value="${employee.login}"/></td>
                <td><c:out value="${employee.password}"/></td>
                <td><c:out value="${employee.name}"/></td>
                <td><c:out value="${employee.surname}"/></td>
                <td><c:out value="${employee.patronymic}"/></td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.mobilePhone}"/></td>
                <td><c:out value="${employee.comment}"/></td>

                <form method="post" action="" name="all_employee_form">
                <td>
                    <select name="e_account_role">
                        <option value="employee" ${employee.accountRole == 'EMPLOYEE' ? 'selected' : ''}>
                            <fmt:message key="registration.accountRole.employee" />
                        </option>

                        <option value="admin" ${employee.accountRole == 'ADMIN' ? 'selected' : ''}>
                            <fmt:message key="registration.accountRole.admin" />
                        </option>
                    </select></td>
                    <td>
                        <input type="hidden" name="id" value="${employee.id}">
                        <input type="submit" name="changeRole"
                            value=<fmt:message key="employeeInformation.table.button.changeRole"/>
                            onClick='this.form.action="employee_change_role";'></td>

                    <td><input type="submit" name="delete"
                            value=<fmt:message key="employeeInformation.table.button.delete"/>
                            onClick='this.form.action="employee_delete";' ></td>
                </form>
            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../jspParts/footer.jsp"/>