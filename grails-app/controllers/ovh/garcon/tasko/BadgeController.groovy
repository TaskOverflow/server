package ovh.garcon.tasko

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BadgeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Badge.list(params), model:[badgeCount: Badge.count()]
    }

    def show(Badge badge) {
        respond badge
    }

    def create() {
        respond new Badge(params)
    }

    @Transactional
    def save(Badge badge) {
        if (badge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (badge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond badge.errors, view:'create'
            return
        }

        badge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'badge.label', default: 'Badge'), badge.id])
                redirect badge
            }
            '*' { respond badge, [status: CREATED] }
        }
    }

    def edit(Badge badge) {
        respond badge
    }

    @Transactional
    def update(Badge badge) {
        if (badge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (badge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond badge.errors, view:'edit'
            return
        }

        badge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'badge.label', default: 'Badge'), badge.id])
                redirect badge
            }
            '*'{ respond badge, [status: OK] }
        }
    }

    @Transactional
    def delete(Badge badge) {

        if (badge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        badge.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'badge.label', default: 'Badge'), badge.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
