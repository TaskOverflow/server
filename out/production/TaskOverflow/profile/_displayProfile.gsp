<div class="jumbotron col-xs-12">
    <div class="col-sm-offset-2 col-sm-8 col-xs-offset-1 col-xs-10">
        <div class="row">
        <div class="col-xs-4">
            <g:if test="${p.image!="0"}">
                <img class="profil-image" src="${p.image}" />
            </g:if>
            <g:else>
                <img class="profil-image" src="${resource(dir: 'images', file: 'user.png')}" />
            </g:else>
            <g:if test="${sec.username()==p.user.username}">
                <g:link class="edit" action="edit" resource="${p}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            </g:if>

        </div>
        <div class="row col-xs-8">
            <h4 class="col-xs-12"><strong><g:message code="other.firstname"/>:</strong> ${p.firstname}
            <g:if test="${!p.firstname}">
                <g:message code="other.unknown"/>
            </g:if>
            </h4>
            <h4 class="col-xs-12"><strong><g:message code="other.lastname"/>:</strong> ${p.lastname}
            <g:if test="${!p.lastname}">
                <g:message code="other.unknown"/>
            </g:if>
            </h4>
            <h4 class="col-xs-12"><strong><g:message code="other.email"/>:</strong> ${p.email}
            <g:if test="${!p.email}">
                <g:message code="other.unknown"/>
            </g:if>
            </h4>
        </div>
        </div>
    </div>
</div>