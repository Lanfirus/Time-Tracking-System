<!DOCTYPE html>

<head>

    <title><fmt:message key="header.title" /></title>

    <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/bootstrap.css" >
    <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/main.css" >
    <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/base.scss" >
    <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8888/company/resources/css/header.scss" >

</head>

<body>

    <header class="header">
        <div class="header__container">
            <a href="/company/tts" class="header__logo"></a>

            <nav class="main-menu header__item">
                <ul class="main-menu__ul">

                        <li class="main-menu__item  main-menu__item--parent ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/admin/task">
                                <fmt:message key="header.task.show.all" />
                            </a>
                            <ul class="sub-menu">

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/task_show_not_assigned">
                                        <fmt:message key="header.task.show.notAssigned" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/task_show_assigned">
                                        <fmt:message key="header.task.show.assigned" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/task_show_finished">
                                        <fmt:message key="header.task.show.finished" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/task_show_expired">
                                        <fmt:message key="header.task.show.expired" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/task_show_cancelled">
                                        <fmt:message key="header.task.show.cancelled" />
                                    </a>
                                </li>


                            </ul>
                        </li>

                        <li class="main-menu__item  main-menu__item--parent ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/admin/project">
                                <fmt:message key="header.project.show.all" />
                            </a>
                            <ul class="sub-menu">

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/project_show_active">
                                        <fmt:message key="header.project.show.active" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/project_show_empty">
                                        <fmt:message key="header.project.show.empty" />
                                    </a>
                                </li>

                            </ul>
                        </li>

                        <li class="main-menu__item ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/admin/employee">
                                <fmt:message key="header.employee.show.all" />
                            </a>
                        </li>

                        <li class="main-menu__item  main-menu__item--parent ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/admin/archive">
                                <fmt:message key="header.archive.show.all" />
                            </a>
                            <ul class="sub-menu">

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/archive_tasks">
                                        <fmt:message key="header.archive.show.tasks" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/archive_projects">
                                        <fmt:message key="header.archive.show.projects" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/admin/archive_employees">
                                        <fmt:message key="header.archive.show.employees" />
                                    </a>
                                </li>

                            </ul>
                        </li>

                        <li class="main-menu__item ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/profile">
                                <fmt:message key="header.profile" />
                            </a>
                        </li>

                </ul>
            </nav>

            <a href="${pageContext.request.contextPath}/tts/logout" class="button button--header"><fmt:message key="header.logout" /></a>
            <div class="burger"><span class="burger__line"></span></div>
        </div>
    </header>
