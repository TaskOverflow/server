<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
        <title><g:message code="home.nav.tags" /></title>
    </head>
    <body class="container-fluid">
        <h1 class="col-sm-offset-2 page-title"><g:message code="home.nav.tags"/></h1>

        <g:set var="foo" value="0" scope="page" />
        <div class="col-sm-offset-2 col-sm-4 col-xs-12">
        <g:each in="${ tagList }" var="b">
            <g:if test="${foo=="0"}">
                <g:render template="/tag/displayTag" model="['b':b]" />
                <g:set var="foo" value="1" scope="page" />
            </g:if>
            <g:else>
                <g:set var="foo" value="0" scope="page" />
            </g:else>
        </g:each>
        </div>
        <div class="col-sm-4 col-xs-12">
        <g:each in="${ tagList }" var="b">
            <g:if test="${foo=="1"}">
                <g:render template="/tag/displayTag" model="['b':b]" />
                <g:set var="foo" value="0" scope="page" />
            </g:if>
            <g:else>
                <g:set var="foo" value="1" scope="page" />
            </g:else>
        </g:each>
        </div>
    </body>
</html>