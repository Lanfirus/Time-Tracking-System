<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-employee.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="main">
    <h2><fmt:message key="employee.mytasks.title" /></h2>

    <c:if test = "${not empty requestScope.task_update_ok}">
    <br>
    <h5>You have updated your task.</h5>
    </c:if>

    <c:if test = "${not empty requestScope.bad_task_update_data}">
    <br>
    <h5 style="color:red;">Warning: You have input incorrect data. System can not update your task.</h5>
    </c:if>

    <c:if test = "${not empty requestScope.task_status_changed}">
    <br>
    <h5 style="color:red;">Warning: Task data has been changed before your update.
        Check new data and repeat update if needed.
    </h5>
    </c:if>

    <table>
        <tr>
            <th><fmt:message key="employee.mytasks.id"/></th>
            <th><fmt:message key="employee.mytasks.projectId"/></th>
            <th><fmt:message key="employee.mytasks.name"/></th>
            <th><fmt:message key="employee.mytasks.deadline"/></th>
            <th><fmt:message key="employee.mytasks.spentTime"/></th>
            <th><fmt:message key="employee.mytasks.status"/></th>
            <th><fmt:message key="employee.mytasks.approved"/></th>
            <th><fmt:message key="employee.mytasks.changeStatus"/></th>
        </tr>

        <c:forEach var="task" items="${myTasks}">
            <tr>
                <td><c:out value="${task.id}"/></td>
                <td><c:out value="${task.projectId}"/></td>
                <td><c:out value="${task.name}"/></td>
                <td><c:out value="${task.deadline}"/></td>

                <c:choose>

                    <c:when test = "${( (task.status == 'FINISHED') || (task.status == 'CANCELLED') ) &&
                        (task.approved == 'YES' )}">
                        <td><c:out value="${task.spentTime}"/></td>
                        <td><c:out value="${fn:toLowerCase(task.status)}"/></td>
                        <td><c:out value="${fn:toLowerCase(task.approved)}"/></td>
                    </c:when>

                    <c:otherwise>

                        <form method="post" action="" name="my_tasks_form">
                        <td>
                            <input type="number"  min="0" style="width: 5em" name="task_spent_time" required
                                value="${task.spentTime}"/>
                        </td>

                        <td>

                            <select name="task_status">
                                <option value="assigned" ${task.status == 'ASSIGNED' ? 'selected' : ''}>
                                    <fmt:message key="employee.mytasks.status.assigned" />
                                </option>

                                 <option value="finished" ${task.status == 'FINISHED' ? 'selected' : ''}>
                                    <fmt:message key="employee.mytasks.status.finished" />
                                 </option>

                                 <option value="cancelled" ${task.status == 'CANCELLED' ? 'selected' : ''}>
                                    <fmt:message key="employee.mytasks.status.cancelled" />
                                 </option>
                            </select>

                        </td>

                        <td><c:out value="${fn:toLowerCase(task.approved)}"/></td>

                        <td>
                            <input type="hidden" name="task_id" value="${task.id}">
                            <input type="hidden" name="old_status" value="${task.status}">
                            <input type="submit" name="changeStatus"
                                 value=<fmt:message key="employee.mytasks.changeStatus"/>
                                 onClick='this.form.action="task_show";'>
                        </td>
                        </form>

                    </c:otherwise>

                </c:choose>

            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="${pageContext.request.contextPath}/tts/new_task_form" class="button">
        <fmt:message key="employee.mytasks.button.requestNew" />
    </a>

</div>

<jsp:include page="../jspParts/footer.jsp"/>