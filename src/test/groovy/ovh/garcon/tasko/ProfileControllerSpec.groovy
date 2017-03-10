package ovh.garcon.tasko

import grails.test.mixin.*
import spock.lang.*

@TestFor(ProfileController)
@Mock([Profile, User])
class ProfileControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["firstname"] = 'a'
        params["lastname"] = 'a'
        params["email"] = 'a'
        params["image"] = 'a'
        params["user"] = new User(username: 'a', password: 'a', id: 1)
        params["user.id"] = 1
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def profile = new Profile()
            profile.setUser(new User())
            profile.validate()
            controller.save(profile)

        then:"The create view is rendered again with the correct model"
            model.profile== null
            view == null

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            profile = new Profile(params)

            controller.save(profile)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/user/show'
            controller.flash.message != null
            Profile.count() == 2
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def profile = new Profile(params)
            controller.edit(profile)

        then:"A model is populated containing the domain instance"
            model.profile == profile
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/profile/index'
            flash.message != null

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            def profile = new Profile(params).save(flush: true)
            controller.update(profile)

        then:"A redirect is issued to the show action"
            profile != null
            response.redirectedUrl == "/user/show"
            flash.message != null
    }

}
