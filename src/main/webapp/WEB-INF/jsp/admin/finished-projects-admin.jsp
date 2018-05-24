<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-admin.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<div class="main">
    <h2><fmt:message key="admin.allprojects.finishedProjects.title" /></h2>

    <form method="post" action="" name="finished_projects_form">

            <display:table name="myProjects" id="project"
                           sort="list" requestURI = "" pagesize="5">
                <display:column property="id" titleKey="admin.allprojects.id"
                                sortable="true" headerClass="sortable" />
                <display:column property="name" titleKey="admin.allprojects.name"
                                sortable="true" headerClass="sortable" />
                <display:column property="status" titleKey="admin.allprojects.status"
                                sortable="true" headerClass="sortable" />
                <display:column titleKey="admin.allprojects.deadline" sortable="true" headerClass="sortable" >
                    <fmt:parseDate value="${project.deadline}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                    <fmt:formatDate value="${parsedDate}" type="date" dateStyle = "short"/>
                </display:column>
                <display:column titleKey="admin.allprojects.select" >
                    <input type="radio" name="project_id" value="${project.id}" checked>
                </display:column>
            </display:table>

<div class="but">
            <button class="submit" type="submit" onClick="return check(this);" name="edit" >
                <fmt:message key="admin.allprojects.button.edit" />
            </button>
            <br>
            <button class="submit" type="submit" onClick="return check(this);" name="delete" >
                <fmt:message key="admin.allprojects.button.delete" />
            </button>
            <br>
            <button class="submit" type="submit" onClick="return check(this);" name="archive" >
                            <fmt:message key="admin.allprojects.button.archive" />
            </button>
</div>

    </form>

    <br>
    <a href="${pageContext.request.contextPath}/tts/admin/new_project_form" class="button"">
        <fmt:message key="admin.allprojects.button.newProject" />
    </a>

</div>

<script>
function check(button) {
    <c:if test="${empty project.id}">
        return false;
    </c:if>
    var form = button.form;
    if(button.name == "edit") {
        form.action = "project_edit_form";
    }
    else if(button.name == "delete") {
        form.action = "project_delete";
    }
    else {
        form.action = "project_archive";
    }
    return true;
}
</script>

<jsp:include page="../jspParts/footer.jsp"/>