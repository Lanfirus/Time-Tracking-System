<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title>Time Tracking System</title>

    <link rel="stylesheet" media="screen" href="resources/css/main.css" >
    <link rel="stylesheet" media="screen" href="resources/css/bootstrap.css" >
    <link rel="stylesheet" media="screen" href="resources/css/base.scss" >
    <link rel="stylesheet" media="screen" href="resources/css/home-page.scss" >
    <link rel="stylesheet" media="screen" href="resources/css/header.scss" >

</head>

<body>

    <header class="header">
        <div class="header__container">
            <a href="/company/tts" class="header__logo"></a>

            <div>
                <form action="${pageContext.request.contextPath}/app/language" method="post">
                    <select name="language">
                        <option value="en" ${language == 'en' ? 'selected' : ''}>
                                <fmt:message key="header.language.english" />
                        </option>
                        <option value="ua" ${language == 'ua' ? 'selected' : ''}>
                            <fmt:message key="header.language.ukrainian" />
                        </option>
                    </select>
                    <button class="button-select" type="submit"><fmt:message key="header.language.select" /></button>
                </form>
            </div>
            <br>
            <a href="${pageContext.request.contextPath}/tts/registration" class="button button--header"><fmt:message key="header.registration" /></a>
            <div class="burger"><span class="burger__line"></span></div>

        </div>
    </header>
