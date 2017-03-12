package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * Manage users' profile
 */
@Transactional(readOnly = true)
class ProfileController {

    static responseFormats = ['json', 'xml']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Profile profile) {
        respond profile
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
                redirect controller: "user", action: "show", id: profile.user.id
            }
            '*' { respond profile, [status: CREATED] }
        }
    }

    //@Secured(['ROLE_USER', 'ROLE_ADMIN'])
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
                redirect controller: "user", action: "show", id: profile.user.id
            }
            '*'{ respond profile, [status: OK] }
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
