<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body class="container-fluid">
            <h1 class="col-sm-offset-2 page-title"><g:link controller="tag" action="index"><g:message code="home.nav.tags"/></g:link> > ${this.tag.label}</h1>
            <div class="col-sm-offset-2 col-sm-8">
                <g:render template="/tag/displayTag" model="['b':this.tag]" />
            </div>
    </body>
</html>
