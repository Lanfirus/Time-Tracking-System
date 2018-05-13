<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-employee.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="main">
    <h2><fmt:message key="employee.newtask.title" /></h2>

    <c:if test = "${not empty requestScope.task_request_ok}">
    <br>
    <h5>You have created request for your task.</h5>
    </c:if>

    <c:if test = "${not empty requestScope.bad_task_request_data}">
    <br>
    <h5 style="color:red;">Warning: You have input incorrect data. System can not update your task.</h5>
    </c:if>

    <form method="post" action="" name="my_tasks_form">

        <li>
            <label for="Project"><fmt:message key="employee.newtask.project" />:</label>

                <select name="project_id" required>
                    <c:forEach var="project" items="${myProjects}">

                        <option value="${project.id}">
                            <fmt:message key="employee.newtask.projectName" />:${project.name}
                        </option>
                        <c:out value="${project.deadline}"/>

                    </c:forEach>
                </select>

        </li>

        <li>
            <label for="name"><fmt:message key="employee.newtask.name" />:</label>
            <input type="text" name="name" required pattern=".+" />
        </li>

        <li>
            <label for="deadline"><fmt:message key="employee.newtask.deadline" />:</label>
            <input type="date" name="deadline" />
        </li>

        <li>
            <label for="spent_time"><fmt:message key="employee.newtask.spentTime" />:</label>
            <input type="number" style="width: 5em" name="spent_time" min = "0"/>
        </li>

    <br>
    <a href="${pageContext.request.contextPath}/tts/request_new_task" class="button">
        <fmt:message key="employee.newtask.button.requestNew" />
    </a>

</div>

<jsp:include page="../jspParts/footer.jsp"/>