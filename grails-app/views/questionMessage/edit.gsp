<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'questionMessage.label', default: 'QuestionMessage')}" />
    <title><g:message code="default.button.edit.label" /></title>
</head>
<body class="container-fluid">
<h1 class="col-sm-offset-2 col-xs-offset-1 page-title">
    <g:link controller="question" action="show" id="${questionMessage.question.id}">
        <g:message code="other.back"/></g:link> > <g:message code="other.new"/>
</h1>
<div class="jumbotron col-sm-offset-2 col-xs-offset-1 col-xs-10 col-sm-8 form-group">
    <div id="edit-questionMessage" class="content scaffold-edit" role="main">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${this.questionMessage}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.questionMessage}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>

        <g:form resource="${this.questionMessage}" method="PUT">
            <g:hiddenField name="version" value="${this.questionMessage?.version}" />
            <fieldset class="form">
                <f:field property="content" bean="questionMessage">
                    <g:textArea name="content" style="height: 200px; text-align: justify;" class="form-control" value="${questionMessage.content}"/>
                </f:field>
            </fieldset>
            <fieldset class="text-center">
                <input class="save" type="submit" value="${message(code: 'other.send')}" />
            </fieldset>
        </g:form>
    </div>
</div>
</body>
</html>
