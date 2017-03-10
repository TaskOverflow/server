package ovh.garcon.tasko

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AnswerMessageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AnswerMessage.list(params), model:[answerMessageCount: AnswerMessage.count()]
    }

    def show(AnswerMessage answerMessage) {
        respond answerMessage
    }

    def create() {
        respond new AnswerMessage(params)
    }

    @Transactional
    def save(AnswerMessage answerMessage) {
        if (answerMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (answerMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond answerMessage.errors, view:'create'
            return
        }

        answerMessage.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'answerMessage.label', default: 'AnswerMessage'), answerMessage.id])
                redirect answerMessage
            }
            '*' { respond answerMessage, [status: CREATED] }
        }
    }

    def edit(AnswerMessage answerMessage) {
        respond answerMessage
    }

    @Transactional
    def update(AnswerMessage answerMessage) {
        if (answerMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (answerMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond answerMessage.errors, view:'edit'
            return
        }

        answerMessage.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'answerMessage.label', default: 'AnswerMessage'), answerMessage.id])
                redirect answerMessage
            }
            '*'{ respond answerMessage, [status: OK] }
        }
    }

    @Transactional
    def delete(AnswerMessage answerMessage) {

        if (answerMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        answerMessage.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'answerMessage.label', default: 'AnswerMessage'), answerMessage.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerMessage.label', default: 'AnswerMessage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
