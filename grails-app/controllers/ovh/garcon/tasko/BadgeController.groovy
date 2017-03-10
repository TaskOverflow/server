package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * Manage badges
 */
@Transactional(readOnly = true)
class BadgeController {

    static responseFormats = ['json', 'xml']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Badge.list(params), model:[badgeCount: Badge.count()]
    }

    def show(Badge badge) {
        respond badge
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
            respond badge.errors, view:'index'
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
