<div class="jumbotron col-sm-offset-2 col-xs-12 col-sm-8">
    <div class="row">
        <img class="col-sm-3 col-xs-4 fix-image" src="${resource(dir: 'images', file: 'badge.png')}" alt="image"/>
        <div class="row col-sm-9 col-xs-8"><h2 class="text-center col-xs-12">${b.label}</h2>
        <h3 class="col-xs-12"><g:message code="${b.description}"/></h3></div>
    </div>

    <strong><g:message code="home.badge.owners"/></strong>
    <g:each in="${b.users}" var="u">
        <g:link controller="user" action="show" id="${u.id}">${u.username}</g:link>
    </g:each>
    <g:if test="${b.users.size()==0}">
        <g:message code="other.none"/>
    </g:if>
</div>