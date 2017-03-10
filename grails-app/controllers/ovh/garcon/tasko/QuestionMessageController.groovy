package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * Manage message for questions
 */
@Transactional(readOnly = true)
class QuestionMessageController {

    static responseFormats = ['json', 'xml']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

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
                redirect controller:"question", action:"show", id:questionMessage.question.id, method:"GET"
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
                redirect controller:"question", action:"show", id:questionMessage.question.id, method:"GET"
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
