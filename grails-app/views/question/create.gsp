<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="home.nav.ask"/></title>
    </head>
    <body class="container-fluid">
    <br/>
        <div id="create-question" class="col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 content panel panel-default" role="main">
            <h1 class="page-title"><g:message code="home.nav.ask"/></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.question}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.question}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save" resource="${this.question}">
                <fieldset class="form">
                    <f:field property="title" bean="question" class="form-control">
                        <input name="title" type="title" class="form-control">
                    </f:field>

                    <f:field property="tags" bean="question" class="form-control"/>
                    <div style="display: none">
                        <f:field property="isSolved" value="false" bean="question" class="hiddenField"/>
                    </div>
                    <f:field property="question.content" bean="question" class="form-control">
                        <g:textArea name="content" class="form-control" style="height: 200px"/>
                    </f:field>
                    <g:hiddenField name="mycontent" value="${content}"/>
                    <g:hiddenField name="username" value="${sec.username()}"/>

                </fieldset>
                <fieldset class="text-center">
                    <g:submitButton name="create" class="btn btn-default" value="${message(code: 'other.send')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
