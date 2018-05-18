<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-admin.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<div class="main">
    <h2><fmt:message key="admin.allprojects.title" /></h2>

    <c:if test = "${not empty requestScope.project_update_ok}">
    <br>
    <h5>You have updated your task.</h5>
    </c:if>

    <c:if test = "${not empty requestScope.bad_project_update_data}">
    <br>
    <h5 style="color:red;">Warning: You have input incorrect data. System can not update your task.</h5>
    </c:if>

    <form method="post" action="" name="all_projects_form">

            <display:table name="myProjects" id="project"
                           sort="list" requestURI = "" pagesize="5">
                <display:column property="id" titleKey="admin.allprojects.id"
                                sortable="true" headerClass="sortable" />
                <display:column property="name" titleKey="admin.allprojects.name"
                                sortable="true" headerClass="sortable" />
                <display:column property="status" titleKey="admin.allprojects.status"
                                sortable="true" headerClass="sortable" />
                <display:column property="deadline" titleKey="admin.allprojects.deadline"
                                sortable="true" headerClass="sortable" />
                <display:column titleKey="admin.allprojects.select" >
                    <input type="radio" name="project_id" value="${project.id}" checked>
                </display:column>
            </display:table>

<div class="but">
            <button class="submit" type="submit" onClick='this.form.action="project_edit_form";'>
                <fmt:message key="admin.allprojects.button.edit" />
            </button>
            <br>
            <button class="submit" type="submit" onClick='this.form.action="project_delete";'>
                <fmt:message key="admin.allprojects.button.delete" />
            </button>
            <br>
            <button class="submit" type="submit" onClick='this.form.action="project_archive";'>
                            <fmt:message key="admin.allprojects.button.archive" />
            </button>
</div>

    </form>

    <br>
    <a href="${pageContext.request.contextPath}/tts/admin/new_project_form" class="button"">
        <fmt:message key="admin.allprojects.button.newProject" />
    </a>

</div>

<jsp:include page="../jspParts/footer.jsp"/>