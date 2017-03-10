<div class="jumbotron badgeSize">
    <div class="row">
        <div class="text-center col-xs-12 ">
            <img class="profil-image" src="${resource(dir: 'images', file: 'badge.png')}" alt="image"/>
        </div>
        <h2 class="text-center col-xs-12"><g:link controller="badge" action="show" id="${b.id}">${b.label}</g:link></h2>
    </div>
</div>