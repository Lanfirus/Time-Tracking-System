<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-employee.jsp"%>

<div class="main">
    <h2><fmt:message key="employee.mytasks.title" /></h2>

    <table>
        <tr>
            <th><fmt:message key="employee.mytasks.id"/></th>
            <th><fmt:message key="employee.mytasks.name"/></th>
            <th><fmt:message key="employee.mytasks.projectId"/></th>
            <th><fmt:message key="employee.mytasks.status"/></th>
            <th><fmt:message key="employee.mytasks.deadline"/></th>
            <th><fmt:message key="employee.mytasks.spentTime"/></th>
        </tr>

        <c:forEach var="task" items="${myTasks}">
            <tr>
                <td><c:out value="${task.id}"/></td>
                <td><c:out value="${task.name}"/></td>
                <td><c:out value="${task.projectId}"/></td>
                <td><c:out value="${task.deadline}"/></td>

                <form method="post" action="" name="my_tasks_form">
                    <td>
                        <input type="time" name="t_spent_time" required value="${task.spentTime}"/>
                    </td>

                    <td>

                    <select name="t_status">
                        <option value="task" ${task.status == 'ASSIGNED' ? 'selected' : ''}>
                            <fmt:message key="employee.mytasks.status.assigned" />
                        </option>

                        <option value="task" ${task.status == 'FINISHED' ? 'selected' : ''}>
                            <fmt:message key="employee.mytasks.status.finished" />
                        </option>

                        <option value="task" ${task.status == 'EXPIRED' ? 'selected' : ''}>
                            <fmt:message key="employee.mytasks.status.expired" />
                        </option>

                        <option value="task" ${task.status == 'CANCELLED' ? 'selected' : ''}>
                            <fmt:message key="employee.mytasks.status.cancelled" />
                        </option>
                    </select>

                    </td>

                    <td>
                        <input type="hidden" name="id" value="${task.id}">
                        <input type="submit" name="changeStatus"
                            value=<fmt:message key="employee.mytasks.changeStatus"/>
                            onClick='this.form.action="task_change_status";'>
                    </td>
                </form>

            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../jspParts/footer.jsp"/>