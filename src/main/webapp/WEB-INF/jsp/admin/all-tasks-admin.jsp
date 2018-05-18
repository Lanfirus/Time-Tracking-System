<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-admin.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<div class="main">
    <h2><fmt:message key="admin.alltasks.title" /></h2>

    <c:if test = "${not empty requestScope.task_update_ok}">
    <br>
    <h5>You have updated your task.</h5>
    </c:if>

    <c:if test = "${not empty requestScope.bad_task_update_data}">
    <br>
    <h5 style="color:red;">Warning: You have input incorrect data. System can not update your task.</h5>
    </c:if>

    <form method="post" action="" name="my_tasks_form">

            <display:table name="myTasks" sort="list" id="task" requestURI = "" pagesize="5">
                <display:column property="id" titleKey="admin.alltasks.id"
                                sortable="true" headerClass="sortable" ></display:column>
                <display:column property="projectId" titleKey="admin.alltasks.projectId"
                                sortable="true" headerClass="sortable" />
                <display:column property="employeeId" titleKey="admin.alltasks.employeeId"
                                sortable="true" headerClass="sortable" />
                <display:column property="name" titleKey="admin.alltasks.name"
                                sortable="true" headerClass="sortable" />
                <display:column property="status" titleKey="admin.alltasks.status"
                                sortable="true" headerClass="sortable" />
                <display:column property="deadline" titleKey="admin.alltasks.deadline"
                                sortable="true" headerClass="sortable" />
                <display:column property="spentTime" titleKey="admin.alltasks.spentTime"
                                sortable="true" headerClass="sortable" />
                <display:column property="approvalState" titleKey="admin.alltasks.approvalState"
                                sortable="true" headerClass="sortable" />
                <display:column titleKey="admin.alltasks.select" >
                <input type="radio" name="task_id" value="${task.id}" checked>
                </display:column>

            </display:table>

            <button class="submit" type="submit" onClick='this.form.action="task_edit_form";'>
                <fmt:message key="admin.alltasks.button.edit" />
            </button>
            <br>
            <button class="submit" type="submit" onClick='this.form.action="task_delete";'>
                <fmt:message key="admin.alltasks.button.delete" />
            </button>

    </form>

    <br>
        <a href="${pageContext.request.contextPath}/tts/admin/new_task_form" class="button">
            <fmt:message key="admin.taskedit.button.newTask" />
        </a>

</div>

<jsp:include page="../jspParts/footer.jsp"/>