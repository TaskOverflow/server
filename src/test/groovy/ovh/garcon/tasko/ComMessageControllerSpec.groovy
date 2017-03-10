package ovh.garcon.tasko

import grails.test.mixin.*
import spock.lang.*

@TestFor(ComMessageController)
@Mock([ComMessage,User,BadgatorService])
class ComMessageControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["content"] = 'bonjour'
        params["date"] = new Date()
        params["value"] = 0
        params["user"] = new User(username: "b", password: "p")
        params["question"] = new Question(id: 1)
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.comMessage!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def comMessage = new ComMessage()
            comMessage.validate()
            controller.save(comMessage)

        then:"The create view is rendered again with the correct model"
            model.comMessage!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            comMessage = new ComMessage(params)

            controller.save(comMessage)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/comMessage/show/1'
            controller.flash.message != null
            ComMessage.count() == 1
    }
}
