<!DOCTYPE html>

<head>

    <title><fmt:message key="header.title" /></title>

    <link rel="stylesheet" type="text/css" href="http://localhost:8888/company/resources/css/bootstrap.css" >
    <link rel="stylesheet" type="text/css" href="http://localhost:8888/company/resources/css/base.css" >
    <link rel="stylesheet" type="text/css" href="http://localhost:8888/company/resources/css/header.css" >
    <link rel="stylesheet" type="text/css" href="http://localhost:8888/company/resources/css/footer.css" >
    <link rel="stylesheet" type="text/css" href="http://localhost:8888/company/resources/css/reg-form.css" >

</head>

<body>

    <header class="header">
        <div class="header-container">
            <a href="/company/tts" class="header-logo"></a>

            <nav class="main-menu header-item">
                <ul class="main-menu-ul">

                        <li class="main-menu-item  main-menu-item-parent ">
                                                    <a class="main-menu-link" href="${pageContext.request.contextPath}/tts/admin/task">
                                <fmt:message key="header.task.show.all" />
                            </a>
                            <ul class="sub-menu">

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/task_show_not_assigned">
                                        <fmt:message key="header.task.show.notAssigned" />
                                    </a>
                                </li>

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/task_show_assigned">
                                        <fmt:message key="header.task.show.assigned" />
                                    </a>
                                </li>

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/task_show_finished">
                                        <fmt:message key="header.task.show.finished" />
                                    </a>
                                </li>

                                <li class="sub-menu-li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/task_show_expired">
                                        <fmt:message key="header.task.show.expired" />
                                    </a>
                                </li>

                                <li class="sub-menu-li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/task_show_cancelled">
                                        <fmt:message key="header.task.show.cancelled" />
                                    </a>
                                </li>


                            </ul>
                        </li>

                        <li class="main-menu-item  main-menu-item-parent ">
                            <a class="main-menu-link" href="${pageContext.request.contextPath}/tts/admin/project">
                                <fmt:message key="header.project.show.all" />
                            </a>
                            <ul class="sub-menu">

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/project_show_active">
                                        <fmt:message key="header.project.show.active" />
                                    </a>
                                </li>

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/project_show_empty">
                                        <fmt:message key="header.project.show.empty" />
                                    </a>
                                </li>

                            </ul>
                        </li>

                        <li class="main-menu-item ">
                            <a class="main-menu-link" href="${pageContext.request.contextPath}/tts/admin/employee">
                                <fmt:message key="header.employee.show.all" />
                            </a>
                        </li>

                        <li class="main-menu-item  main-menu-item-parent ">
                            <a class="main-menu-link" href="${pageContext.request.contextPath}/tts/admin/archive">
                                <fmt:message key="header.archive.show.all" />
                            </a>
                            <ul class="sub-menu">

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/archive_tasks">
                                        <fmt:message key="header.archive.show.tasks" />
                                    </a>
                                </li>

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/archive_projects">
                                        <fmt:message key="header.archive.show.projects" />
                                    </a>
                                </li>

                                <li class="sub-menu-li">
                                    <a class="sub-menu-link" href="${pageContext.request.contextPath}/tts/admin/archive_employees">
                                        <fmt:message key="header.archive.show.employees" />
                                    </a>
                                </li>

                            </ul>
                        </li>

                        <li class="main-menu-item ">
                            <a class="main-menu-link" href="${pageContext.request.contextPath}/tts/profile">
                                <fmt:message key="header.profile" />
                            </a>
                        </li>

                </ul>
            </nav>

            <a href="${pageContext.request.contextPath}/tts/logout" class="button button-header"><fmt:message key="header.logout" /></a>
        </div>
    </header>
