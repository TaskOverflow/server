<!doctype html>
<html class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="TaskOverflow"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
                    <i class="fa grails-icon">
                        <asset:image src="favicon.png"/>
                    </i> TaskOverflow
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <li><g:link controller="question"><g:message code="home.nav.questions"/></g:link></li>
                    <li><g:link controller="tag"><g:message code="home.nav.tags"/></g:link></li>
                    <li><g:link controller="badge"><g:message code="home.nav.badges"/></g:link></li>
                    <li><g:link controller="user"><g:message code="home.nav.users"/></g:link></li>
                    <sec:ifNotLoggedIn>
                        <li><g:link controller="user" action="create"><g:message code="home.nav.signin"/></g:link></li>
                        <li><g:link controller="login"><g:message code="home.nav.login"/></g:link></li>
                    </sec:ifNotLoggedIn>
                    <sec:ifLoggedIn>
                        <li><g:link controller="user" action="show" id="${sec.loggedInUserInfo(field: 'id')}"><sec:loggedInUserInfo field="username"/></g:link></li>
                        <li><g:link controller="logout"><g:message code="home.nav.logout"/></g:link></li>
                        <li class="gold"><g:link controller="question" action="create"><g:message code="home.nav.ask"/></g:link></li>
                    </sec:ifLoggedIn>
                </ul>
            </div>
        </div>
    </div>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
