<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>TaskOverflow</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<content tag="nav">
    <a href="#" role="button"><g:message code="home.nav.questions"/><span class="caret"></span></a>
    <a href="#" role="button"><g:message code="home.nav.tags"/><span class="caret"></span></a>
    <a href="#" role="button"><g:message code="home.nav.badges"/><span class="caret"></span></a>
    <a href="#" role="button"><g:message code="home.nav.users"/><span class="caret"></span></a>
</content>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1><g:message code="home.welcome"/></h1>

        <p><g:message code="home.presentation"/></p>

        <div id="controllers" role="navigation">
            <h2>Available Controllers:</h2>
            <ul>
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                    <li class="controller">
                        <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                    </li>
                </g:each>
            </ul>
        </div>
    </section>
</div>

</body>
</html>
