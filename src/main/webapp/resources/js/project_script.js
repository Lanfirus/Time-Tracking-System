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