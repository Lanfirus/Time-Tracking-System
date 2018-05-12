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

            <display:table name="myTasks"
                           sort="list" uid="one" requestURI = "" pagesize="5">
                <display:column property="id" titleKey="admin.alltasks.id"
                                sortable="true" headerClass="sortable" />
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
                <display:column property="approved" titleKey="admin.alltasks.approved"
                                sortable="true" headerClass="sortable" />
                <display:column titleKey="admin.alltasks.changeStatus" >
                    <input type="submit" name="changeRole"
                        value=<fmt:message key="employeeInformation.table.button.changeRole"/>
                        onClick='this.form.action="employee_change_role";'>
                </display:column>
                <display:column >
                    <input type="submit" name="delete"
                        value=<fmt:message key="employeeInformation.table.button.delete"/>
                        onClick='this.form.action="employee_delete";' >
                </display:column>
            </display:table>

</div>

<jsp:include page="../jspParts/footer.jsp"/>