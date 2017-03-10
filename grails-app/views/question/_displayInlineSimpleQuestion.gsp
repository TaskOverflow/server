<tr>
    <td class="text-center"><img class="icon-image" src="${q.isSolved==true?resource(dir: 'images', file: 'ok.png'):resource(dir: 'images', file: 'no.png')}" /></td>
    <td class="text-center">${q.value}</td>
    <td class="text-justify"><g:link controller="question" action="show" id="${q.id}">${q.title}</g:link></td>
    <td class="text-center">${q.question.date.format('dd/MM/yyyy HH:mm')}</td>
</tr>