package ovh.garcon.tasko

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProfileController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Profile.list(params), model:[profileCount: Profile.count()]
    }

    def show(Profile profile) {
        respond profile
    }

    def create() {
        respond new Profile(params)
    }

    @Transactional
    def save(Profile profile) {
        if (profile == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (profile.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond profile.errors, view:'create'
            return
        }

        profile.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
                redirect profile
            }
            '*' { respond profile, [status: CREATED] }
        }
    }

    def edit(Profile profile) {
        respond profile
    }

    @Transactional
    def update(Profile profile) {
        if (profile == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (profile.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond profile.errors, view:'edit'
            return
        }

        profile.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
                redirect profile
            }
            '*'{ respond profile, [status: OK] }
        }
    }

    @Transactional
    def delete(Profile profile) {

        if (profile == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        profile.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
