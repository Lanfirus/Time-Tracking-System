<%@ include file="../jspParts/general.jsp" %>
<%@ include file="../jspParts/header-admin.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<div class="main">
    <h2><fmt:message key="admin.archive.projects.title" /></h2>

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
            </display:table>

</div>

<jsp:include page="../jspParts/footer.jsp"/>