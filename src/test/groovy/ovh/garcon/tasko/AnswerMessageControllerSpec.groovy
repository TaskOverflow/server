package ovh.garcon.tasko

import grails.test.mixin.*
import spock.lang.*

@TestFor(AnswerMessageController)
@Mock([AnswerMessage, User, BadgatorService])
class AnswerMessageControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["content"] = 'bonjour'
        params["date"] = new Date()
        params["value"] = 0
        params["user"] = new User(username: "b", password: "p")
        params["question"] = new Question(id: 1)
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def answerMessage = new AnswerMessage()
            answerMessage.validate()
            controller.save(answerMessage)

        then:"The create view is rendered again with the correct model"
            model.answerMessage!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            answerMessage = new AnswerMessage(params)

            controller.save(answerMessage)

        then:"A redirect is issued to the show action"
            response.redirectedUrl.contains '/question/show'
            controller.flash.message != null
            AnswerMessage.count() == 1
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def answerMessage = new AnswerMessage(params)
            controller.edit(answerMessage)

        then:"A model is populated containing the domain instance"
            model.answerMessage == answerMessage
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl.contains('/answerMessage/index')
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def answerMessage = new AnswerMessage()
            answerMessage.validate()
            controller.update(answerMessage)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.answerMessage == answerMessage

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            answerMessage = new AnswerMessage(params).save(flush: true)
            controller.update(answerMessage)

        then:"A redirect is issued to the show action"
            answerMessage != null
            response.redirectedUrl == "/question/show"
            flash.message != null
    }
}
