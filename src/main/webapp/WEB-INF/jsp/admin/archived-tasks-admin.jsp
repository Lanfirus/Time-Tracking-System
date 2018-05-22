<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-admin.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<div class="main">
    <h2><fmt:message key="admin.archive.tasks.title" /></h2>

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
    </display:table>

</div>

<jsp:include page="../jspParts/footer.jsp"/>