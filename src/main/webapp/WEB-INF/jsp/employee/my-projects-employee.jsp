<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-employee.jsp"%>

<div class="main">
    <h2><fmt:message key="employee.myprojects.title" /></h2>

    <table>
        <tr>
            <th><fmt:message key="employee.myprojects.id"/></th>
            <th><fmt:message key="employee.myprojects.name"/></th>
            <th><fmt:message key="employee.myprojects.deadline"/></th>
            <th><fmt:message key="employee.myprojects.status"/></th>
        </tr>

        <c:forEach var="project" items="${myProjects}">
            <tr>
                <td><c:out value="${project.projectId}"/></td>
                <td><c:out value="${project.projectName}"/></td>
                <td><c:out value="${project.projectDeadline}"/></td>
                <td><c:out value="${project.projectStatus}"/></td>
            </tr>
        </c:forEach>

    </table>

</div>

<jsp:include page="../jspParts/footer.jsp"/>