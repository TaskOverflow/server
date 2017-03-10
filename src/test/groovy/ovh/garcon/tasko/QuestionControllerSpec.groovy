package ovh.garcon.tasko

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.*
import spock.lang.*

@TestFor(QuestionController)
@Mock([Question, User, BadgatorService])
class QuestionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["content"] = 'bonjour'
        params["date"] = new Date()
        params["title"] = "bob"
        params["user"] = new User(username: "b", password: "p")
        params["question"] = new Question(title: "t",user: new User(), isSolved: false)
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.questionList
            model.questionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.question!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def question = new Question(title: "bob", isSolved: false, user: new User(), question: new QuestionMessage())
            question.validate()
            controller.save(question)

        then:"The create view is rendered again with the correct model"
            model.question== null

    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def question = new Question(params)
            controller.show(question)

        then:"A model is populated containing the domain instance"
            model.question == question
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def question = new Question(params)
            controller.edit(question)

        then:"A model is populated containing the domain instance"
            model.question == question
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def question = new Question()
            question.validate()
            controller.update(question)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.question == question

    }

}
