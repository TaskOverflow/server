<div class="jumbotron col-xs-12 col-sm-12">
    <div class="row">
        <img class="col-sm-6 my-image" src="${resource(dir: 'images', file: 'favicon.png')}" alt="image"/>
        <div class="row col-sm-6"><h2 class="text-center col-xs-12">${b.label}</h2></div>
    </div>

    <strong><g:message code="home.nav.questions"/></strong>
    <ul class="my-list">
        <g:each in="${b.questions}" var="q">
            <li><g:link controller="question" action="show" id="${q.id}">${q.title}</g:link></li>
        </g:each>
    </ul>
    <g:if test="${b.questions.size()==0}">
        <g:message code="other.none"/>
    </g:if>
</div>