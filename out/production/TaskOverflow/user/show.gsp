<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title>${this.user.username}</title>
    </head>
    <body class="container-fluid">

        <h1 class="text-center page-title">${this.user.username}
            <g:if test="${sec.username()==this.user.username}">
                <g:link class="btn btn-default form-inline my-btn" action="edit" resource="${this.user}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            </g:if>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:form class="form-inline" style="display: inline-block" controller="user" action="ban" resource="${this.user}" method="PUT">
                    <g:submitButton class="btn btn-default form-inline" name="ban" value="${this.user.accountLocked==true?g.message(code:'message.unban'):g.message(code:'message.ban')}" />
                </g:form>
            </sec:ifAllGranted>
        </h1>
        <g:render template="/profile/displayProfile" model="['p':this.user.profil]" />
        <div class="row">
            <h1 class="col-sm-offset-2 page-title"><g:message code="other.mysuccess"/></h1>
            <div class="col-xs-offset-1 col-xs-10">
                <div class="col-sm-4 col-xs-12">
                    <div class="jumbotron badgeSize">
                        <div class="row">
                            <div class="text-center col-xs-12 page-title">
                                <g:message code="other.reputation"/>
                            </div>
                            <h2 class="text-center col-xs-12 page-title">${this.user.getReputation()}</h2>
                        </div>
                    </div>
                </div>
                <g:each in="${ this.user.badges }" var="b">
                    <div class="col-sm-4 col-xs-12">
                        <g:render template="/badge/displaySimpleBadge" model="['b':b]" />
                    </div>
                </g:each>
            </div>
        </div>

        <div class="row">
            <h1 class="col-sm-offset-2 page-title"><g:message code="other.myquestions"/></h1>
            <div class="col-xs-offset-1 col-xs-10">
                <table class="table">
                    <thead>
                    <tr>
                        <th class="text-center"><g:message code="message.solved"/></th>
                        <th class="text-center"><g:message code="message.value"/></th>
                        <th class="text-center"><g:message code="message.question"/></th>
                        <th class="text-center"><g:message code="message.date"/></th>
                    </tr>
                    </thead>
                    <tbody>
                        <g:each in="${ this.user.questions.size() < 10 ? this.user.questions : this.user.questions.subList(0,10) }" var="q">
                            <g:render template="/question/displayInlineSimpleQuestion" model="['q':q]" />
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <h1 class="col-sm-offset-2 page-title"><g:message code="other.mymessages"/></h1>
            <div class="col-xs-offset-1 col-xs-10">
                <table class="table">
                    <thead>
                    <tr>
                        <th class="text-center"><g:message code="message.value"/></th>
                        <th class="text-center"><g:message code="message.content"/></th>
                        <th class="text-center"><g:message code="message.date"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ this.user.messages.size() < 10 ? this.user.messages : this.user.messages.subList(0,10) }" var="m">
                        <g:render template="/myMessage/displayInlineMessage" model="['m':m]" />
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>

    </body>
</html>
