<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title>TaskOverflow</title>
    </head>
    <body class="container-fluid">
        <section class="row">
            <h1 class="col-sm-offset-1 col-xs-10"><g:message code="home.welcome"/></h1>

            <p class="col-xs-12 col-sm-offset-1 col-sm-10 text-justify"><g:message code="home.presentation"/></p>

            <h1 class="col-sm-offset-2 col-xs-8 page-title"><g:message code="home.nav.questions"/></h1>
            <div class="col-xs-offset-1 col-xs-10">
                <table class="table">
                    <thead>
                    <tr>
                        <th class="text-center"><g:message code="message.solved"/></th>
                        <th class="text-center"><g:message code="message.value"/></th>
                        <th class="text-center"><g:message code="message.question"/></th>
                        <th class="text-center"><g:message code="home.nav.tags"/></th>
                        <th class="text-center"><g:message code="message.date"/></th>
                        <th class="text-center"><g:message code="message.author"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ questionList }" var="q">
                        <g:render template="/question/displayInlineQuestion" model="['q':q]" />
                    </g:each>
                    </tbody>
                </table>
            </div>

        </section>
    </body>
</html>