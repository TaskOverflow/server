<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'answerMessage.label', default: 'AnswerMessage')}" />
        <title><g:message code="default.button.edit.label" /></title>
    </head>
    <body class="container-fluid">
        <h1 class="col-sm-offset-2 col-xs-offset-1 page-title">
            <g:link controller="question" action="show" id="${answerMessage.question.id}">
            <g:message code="other.back"/></g:link> > <g:message code="other.new"/>
        </h1>
        <div class="jumbotron col-sm-offset-2 col-xs-offset-1 col-xs-10 col-sm-8 form-group">
            <div id="edit-answerMessage" class="content scaffold-edit" role="main">
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:hasErrors bean="${this.answerMessage}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.answerMessage}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
                </g:hasErrors>

                <g:form resource="${this.answerMessage}" method="PUT">
                    <g:hiddenField name="version" value="${this.answerMessage?.version}" />
                    <fieldset class="form">
                        <f:field property="content" bean="answerMessage">
                            <g:textArea name="content" style="height: 200px; text-align: justify;" class="form-control" value="${answerMessage.content}"/>
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
