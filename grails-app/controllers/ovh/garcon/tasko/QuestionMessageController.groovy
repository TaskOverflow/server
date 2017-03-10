package ovh.garcon.tasko

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuestionMessageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QuestionMessage.list(params), model:[questionMessageCount: QuestionMessage.count()]
    }

    def show(QuestionMessage questionMessage) {
        respond questionMessage
    }

    def create() {
        respond new QuestionMessage(params)
    }

    @Transactional
    def save(QuestionMessage questionMessage) {
        if (questionMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (questionMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond questionMessage.errors, view:'create'
            return
        }

        questionMessage.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questionMessage.label', default: 'QuestionMessage'), questionMessage.id])
                redirect questionMessage
            }
            '*' { respond questionMessage, [status: CREATED] }
        }
    }

    def edit(QuestionMessage questionMessage) {
        respond questionMessage
    }

    @Transactional
    def update(QuestionMessage questionMessage) {
        if (questionMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (questionMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond questionMessage.errors, view:'edit'
            return
        }

        questionMessage.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionMessage.label', default: 'QuestionMessage'), questionMessage.id])
                redirect questionMessage
            }
            '*'{ respond questionMessage, [status: OK] }
        }
    }

    @Transactional
    def delete(QuestionMessage questionMessage) {

        if (questionMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        questionMessage.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionMessage.label', default: 'QuestionMessage'), questionMessage.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionMessage.label', default: 'QuestionMessage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
