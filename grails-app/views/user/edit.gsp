<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.button.edit.label" /></title>
    </head>
    <body class="container-fluid">
        <br/>
        <h1 class="col-xs-offset-1 col-sm-offset-2 page-title"><g:link controller="user" action="show" id="${user.id}"><g:message code="other.back"/></g:link> > <g:message code="default.button.edit.label"/> </h1>

        <div id="edit-user" class="col-xs-offset-1 col-xs-10 col-sm-offset-3 col-sm-6 content panel panel-danger text-center" role="main">
            <g:if test="${sec.username()==user.username}">
                <div class="panel-body">
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
            <g:form resource="${this.user}" method="PUT">
                <g:hiddenField name="version" value="${this.user?.version}" />
                <fieldset class="form">
                    <f:all bean="user"/>
                </fieldset>
                <fieldset class="text-center">
                    <input class="btn btn-default form-inline" type="submit" value="${message(code: 'other.send')}" />
                </fieldset>
            </g:form>
                </div>
            </g:if>
            <g:else>
                <ul class="errors" role="alert"><g:message code="access.forbidden"/> </ul>
            </g:else>
        </div>
    </body>
</html>

















