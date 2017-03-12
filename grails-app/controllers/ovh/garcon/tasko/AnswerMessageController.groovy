package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import ovh.garcon.tasko.BadgatorService

/**
 * Manage answers
 */
@Transactional(readOnly = true)
class AnswerMessageController {

    static responseFormats = ['json', 'xml']

    def badgatorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(AnswerMessage message) {
        respond message
    }

    //@Secured(['ROLE_USER', 'ROLE_ADMIN'])
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

        badgatorService.serviceMethod(answerMessage.getUserId()) // check badges

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'answerMessage.label', default: 'AnswerMessage'), answerMessage.id])
                redirect controller:"question", action:"show", id:answerMessage.question.id, method:"GET"
            }
            '*' { respond answerMessage, [status: CREATED] }
        }
    }

    //@Secured(['ROLE_USER','ROLE_ADMIN'])
    def edit(AnswerMessage answerMessage) {
        respond answerMessage
    }

    //@Secured(['ROLE_USER', 'ROLE_ADMIN'])
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
                redirect controller:"question", action:"show", id:answerMessage.question.id, method:"GET"
            }
            '*'{ respond answerMessage, [status: OK] }
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

    /**
     * Add a new answer
     * @return
     */
    //@Secured(['ROLE_USER','ROLE_ADMIN'])
    @Transactional
    def add(){
        AnswerMessage item = new AnswerMessage(
                date: new Date(),
                content: params.content,
                user: (User)getAuthenticatedUser(),
                value: 0,
                question: Question.get(params.qId as Integer)
        )

        if (item == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (item.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond item.errors, view:'create'
            return
        }

        item.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'answerMessage.label', default: 'AnswerMessage'), item.id])
                redirect controller:"question", action:"show", id:params.qId, method:"GET"
            }
            '*' { respond item, [status: CREATED] }
        }
    }
}
