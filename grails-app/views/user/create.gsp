<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="home.nav.signin" /></title>
    </head>
    <body class="container-fluid">
    <br/>
    <div class="panel panel-danger col-xs-offset-1 col-sm-offset-2 col-xs-10 col-sm-8 col-md-offset-3 col-md-6 form-group">
        <div id="create-user" class="panel-body text-center" role="main">
            <h1 class="page-title"><g:message code="home.nav.signin"/></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save" class="form">
                <fieldset class="form-inline form-group">
                    <f:all bean="user"/>
                </fieldset>
                <fieldset class="text-center">
                    <g:submitButton class="btn btn-default form-inline" name="create" value="${g.message(code:'other.send')}" />
                </fieldset>
            </g:form>
        </div>
    </div>
    </body>
</html>
