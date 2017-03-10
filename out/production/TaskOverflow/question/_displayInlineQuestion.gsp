<tr>
    <td class="text-center"><img class="icon-image" src="${q.isSolved==true?resource(dir: 'images', file: 'ok.png'):resource(dir: 'images', file: 'no.png')}" /></td>
    <td class="text-center">${q.value}</td>
    <td class="text-justify"><g:link controller="question" action="show" id="${q.id}">${q.title}</g:link></td>
    <td class="text-justify">
        <g:each in="${q.tags}" var="t">
            <g:link controller="tag" action="show" id="${t.id}">${t.label}</g:link>
        </g:each>
    </td>
    <td class="text-center">${q.question.date.format('dd/MM/yyyy HH:mm')}</td>
    <td class="text-center"><g:link controller="user" action="show" id="${q.question.user.id}">${q.question.user.username}</g:link></td>
</tr>