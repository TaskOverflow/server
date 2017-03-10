package ovh.garcon.tasko

import grails.test.mixin.*
import spock.lang.*

@TestFor(BadgeController)
@Mock(Badge)
class BadgeControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["label"] = 'Badge'
        params["desc"] = 'You wont have it!'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.badgeList
            model.badgeCount == 0
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
    }
}
