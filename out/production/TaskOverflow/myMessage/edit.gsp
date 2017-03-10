<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'myMessage.label', default: 'MyMessage')}" />
        <title><g:message code="default.edit.label" /></title>
    </head>
    <body class="container-fluid">
        <h1 class="col-sm-offset-2 page-title"><g:link controller="question" action="show" id="${params.questionId}"><g:message code="other.back"/></g:link> > <g:message code="other.new"/> </h1>
        <div class="jumbotron col-sm-offset-2 col-xs-12 col-sm-8 form-group">
            <g:form controller="comMessage" action="add" method="post">
                <g:hiddenField name="qId" value="${params.questionId}" />
                <g:hiddenField name="mId" value="${params.messageId}" />
                <g:hiddenField name="uId" value="${sec.username()}" />
                <div class="row">
                    <label for="content"><g:message code="message.content"/></label>
                    <g:textArea class="form-control" name="content" value="" />
                </div>
                <br/>
                <div class="row text-center">
                    <g:submitButton name="add" value="${g.message(code:'other.send')}" />
                </div>
            </g:form>
        </div>
    </body>
</html>
