<div class="jumbotron">
    <div class="row">
        <div class="col-xs-12 text-center">
            <g:if test="${b.profil.image!="0"}">
                <img class="profil-image" src="${b.profil.image}" />
            </g:if>
            <g:else>
                <img class="profil-image" src="${resource(dir: 'images', file: 'user.png')}" />
            </g:else>
        </div>
        <div class="row col-xs-12">
            <h2 class="text-center col-xs-12"><g:link controller="user" action="show" id="${b.id}">${b.username}</g:link></h2>
        </div>
    </div>
</div>