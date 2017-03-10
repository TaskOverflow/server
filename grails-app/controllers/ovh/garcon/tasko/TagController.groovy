package ovh.garcon.tasko

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TagController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tag.list(params), model:[tagCount: Tag.count()]
    }

    def show(Tag tag) {
        respond tag
    }

    def create() {
        respond new Tag(params)
    }

    @Transactional
    def save(Tag tag) {
        if (tag == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tag.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tag.errors, view:'create'
            return
        }

        tag.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tag.label', default: 'Tag'), tag.id])
                redirect tag
            }
            '*' { respond tag, [status: CREATED] }
        }
    }

    def edit(Tag tag) {
        respond tag
    }

    @Transactional
    def update(Tag tag) {
        if (tag == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tag.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tag.errors, view:'edit'
            return
        }

        tag.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tag.label', default: 'Tag'), tag.id])
                redirect tag
            }
            '*'{ respond tag, [status: OK] }
        }
    }

    @Transactional
    def delete(Tag tag) {

        if (tag == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tag.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), tag.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
