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
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/task_show">
                                <fmt:message key="header.tasks" />
                            </a>
                            <ul class="sub-menu">

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/task_show">
                                        <fmt:message key="header.task.show" />
                                    </a>
                                </li>

                                <li class="sub-menu__li">
                                    <a class="sub-menu__link" href="${pageContext.request.contextPath}/tts/task_request">
                                        <fmt:message key="header.task.request" />
                                    </a>
                                </li>

                            </ul>
                        </li>

                        <li class="main-menu__item ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/projects">
                                <fmt:message key="header.projects" />
                            </a>
                        </li>

                        <li class="main-menu__item ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/profile">
                                <fmt:message key="header.profile" />
                            </a>
                        </li>

                        <li class="main-menu__item ">
                            <a class="main-menu__link" href="${pageContext.request.contextPath}/tts/about">
                                <fmt:message key="header.aboutXTime" />
                            </a>
                        </li>

                </ul>
            </nav>

            <a href="${pageContext.request.contextPath}/tts/logout" class="button button--header"><fmt:message key="header.logout" /></a>
            <div class="burger"><span class="burger__line"></span></div>
        </div>
    </header>
