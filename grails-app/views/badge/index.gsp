<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'badge.label', default: 'Badge')}" />
        <title><g:message code="home.nav.badges" /></title>
    </head>
    <body class="container-fluid">
        <h1 class="col-sm-offset-2 page-title"><g:message code="home.nav.badges"/></h1>
        <g:each in="${ badgeList.reverse(true) }" var="b">
            <g:render template="/badge/displayBadge" model="['b':b]" />
        </g:each>
    </body>
</html>