<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
    <title>${this.question.title}</title>
    </head>
    <body class="container-fluid">
        <div class="col-sm-offset-2 col-sm-8 col-xs-offset-1 col-xs-10">
            <h1 class="page-title">
                <img class="icon-image" src="${this.question.isSolved==true?resource(dir: 'images', file: 'ok.png'):resource(dir: 'images', file: 'no.png')}" />
                ${this.question.title}
                <g:if test="${sec.username()==this.question.user.username}">
                    <g:if test="${this.question.isSolved==false}">
                        <g:form class="form-inline" style="display: inline-block" controller="question" action="solve" method="post">
                            <g:hiddenField name="qId" value="${this.question.id}" />
                            <g:submitButton class="btn btn-default form-inline" name="solve" value="${g.message(code:'message.solved')}" />
                        </g:form>
                    </g:if>
                </g:if>
            </h1>
            <h2><g:message code="home.nav.tags"/>: <g:each in="${this.question.tags}" var="t">
                <g:link controller="tag" action="show" id="${t.id}">${t.label}</g:link>
            </g:each></h2>

            <g:render template="/questionMessage/displayQuestion" model="['q':this.question]" />

            <g:each in="${this.question.answers}" var="a">
                <g:render template="/answerMessage/displayAnswer" model="['a':a]" />
            </g:each>

            <sec:ifLoggedIn>
                <div class="jumbotron col-xs-12 form-group">
                    <g:form controller="answerMessage" action="add" method="post">
                        <g:hiddenField name="qId" value="${this.question.id}" />
                        <g:hiddenField name="uId" value="${sec.username()}" />
                        <div class="row">
                            <label for="content"><g:message code="message.answer"/></label>
                            <g:textArea style="height: 100px;" class="form-control" name="content" value="" />
                        </div>
                        <br/>
                        <div class="row text-center">
                            <g:submitButton name="add" value="${g.message(code:'other.send')}" />
                        </div>
                    </g:form>
                </div>
            </sec:ifLoggedIn>

        </div>
    </body>
    <g:link class="edit" action="edit" resource="${this.question}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
</html>
