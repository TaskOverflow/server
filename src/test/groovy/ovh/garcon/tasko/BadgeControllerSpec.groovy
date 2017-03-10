package ovh.garcon.tasko

import grails.test.mixin.*
import spock.lang.*

@TestFor(BadgeController)
@Mock(Badge)
class BadgeControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.badgeList
            model.badgeCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.badge!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def badge = new Badge()
            badge.validate()
            controller.save(badge)

        then:"The create view is rendered again with the correct model"
            model.badge!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            badge = new Badge(params)

            controller.save(badge)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/badge/show/1'
            controller.flash.message != null
            Badge.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def badge = new Badge(params)
            controller.show(badge)

        then:"A model is populated containing the domain instance"
            model.badge == badge
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def badge = new Badge(params)
            controller.edit(badge)

        then:"A model is populated containing the domain instance"
            model.badge == badge
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/badge/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def badge = new Badge()
            badge.validate()
            controller.update(badge)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.badge == badge

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            badge = new Badge(params).save(flush: true)
            controller.update(badge)

        then:"A redirect is issued to the show action"
            badge != null
            response.redirectedUrl == "/badge/show/$badge.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/badge/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def badge = new Badge(params).save(flush: true)

        then:"It exists"
            Badge.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(badge)

        then:"The instance is deleted"
            Badge.count() == 0
            response.redirectedUrl == '/badge/index'
            flash.message != null
    }
}
