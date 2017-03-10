package ovh.garcon.tasko

import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
@Mock(Tag)
class TagControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["label"] = 'someValidName'

    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.tagList
            model.tagCount == 0
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def tag = new Tag()
            tag.validate()
            controller.save(tag)

        then:"The create view is rendered again with the correct model"
            model.tag!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            tag = new Tag(params)

            controller.save(tag)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/tag/show/1'
            controller.flash.message != null
            Tag.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tag = new Tag(params)
            controller.show(tag)

        then:"A model is populated containing the domain instance"
            model.tag == tag
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/tag/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def tag = new Tag()
            tag.validate()
            controller.update(tag)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.tag == tag

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            tag = new Tag(params).save(flush: true)
            controller.update(tag)

        then:"A redirect is issued to the show action"
            tag != null
            response.redirectedUrl == "/tag/show/$tag.id"
            flash.message != null
    }

}
