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

            <display:table name="myProjects"
                           sort="list" uid="one" requestURI = "" pagesize="5">
                <display:column property="id" titleKey="admin.allprojects.id"
                                sortable="true" headerClass="sortable" />
                <display:column property="name" titleKey="admin.allprojects.name"
                                sortable="true" headerClass="sortable" />
                <display:column property="status" titleKey="admin.allprojects.status"
                                sortable="true" headerClass="sortable" />
                <display:column property="deadline" titleKey="admin.allprojects.deadline"
                                sortable="true" headerClass="sortable" />
                <display:column titleKey="admin.allprojects.edit" >
                    <input type="submit" name="changeRole"
                        value=<fmt:message key="admin.allprojects.button.edit"/>
                        onClick='this.form.action="project_edit";'>
                </display:column>
                <display:column titleKey="admin.allprojects.delete">
                    <input type="submit" name="delete"
                        value=<fmt:message key="admin.allprojects.button.delete"/>
                        onClick='this.form.action="project_delete";' >
                </display:column>
            </display:table>

</div>

<jsp:include page="../jspParts/footer.jsp"/>