<div class="panel ${type}">
    <div class="panel-body ${style}">
        <div class="${style}">
            <div class="col-xs-3">
                <div class="col-xs-12 text-center">

                    <g:form controller="myMessage" action="vote" method="post">
                        <g:hiddenField name="qId" value="${a.question.id}" />
                        <g:hiddenField name="mId" value="${a.id}" />
                        <g:hiddenField name="inc" value="1" />
                        <g:submitButton name="▲" class="btn btn-success btn-lg"/>
                    </g:form>

                </div>
                <div class="col-xs-12 text-center"><h2>${a.value}</h2></div>
                <div class="col-xs-12 text-center">

                    <g:form controller="myMessage" action="vote" method="post">
                        <g:hiddenField name="qId" value="${a.question.id}" />
                        <g:hiddenField name="mId" value="${a.id}" />
                        <g:hiddenField name="inc" value="-1" />
                        <g:submitButton name="▼" class="btn btn-danger btn-lg"/>
                    </g:form>

                </div>
            </div>
            <div class="col-xs-9 text-justify">${a.content}</div>
            <sec:ifLoggedIn>
                <div class="col-xs-12"><br/></div>
                <div class="col-xs-12 text-justify coms"><g:link controller="comMessage" action="create" params="['questionId':a.question.id,'messageId':a.id]"><g:message code="message.addcom"/></g:link></div>
            </sec:ifLoggedIn>
            <g:each in="${a.coms}" var="c">
                <g:render template="/comMessage/displayCom" model="['com':c]"/>
            </g:each>
        </div>
    </div>
    <div class="panel-footer container-fluid">
        <div class="text-left col-xs-6">
            <g:if test="${sec.username()==a.user.username}">
                <g:link class="btn btn-default form-inline my-btn" controller="myMessage" action="edit" resource="${a}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            </g:if>
        </div>
        <div class="text-right col-xs-6">
            <img class="small-profil-image" src="${a.user.profil.image != "0" ? a.user.profil.image : resource(dir: 'images', file: 'user.png')}" />
            <g:link controller="user" action="show" id="${a.user.id}">${a.user.username}</g:link>, ${a.date.format('dd/MM/yyyy HH:mm')}
        </div>

    </div>
</div>