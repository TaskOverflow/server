<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="home.nav.users" /></title>
    </head>
    <body class="container-fluid">
        <h1 class="col-sm-offset-2 page-title"><g:message code="home.nav.users"/></h1>
        <div class="col-sm-offset-2 col-xs-offset-1 col-sm-8 col-xs-10">
        <g:each in="${ userList }" var="b">
            <div class="col-sm-4 col-xs-12">
                <g:render template="/user/displaySimpleUser" model="['b':b]" />
            </div>
        </g:each>
        </div>
    </body>
</html>