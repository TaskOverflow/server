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
 * Manage generic messages
 */
@Transactional(readOnly = true)
class MyMessageController {

    static responseFormats = ['json', 'xml']

    /**
     * Gamification service
     */
    def badgatorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    @Transactional
    def save(MyMessage myMessage) {

        if (myMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (myMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond myMessage.errors, view:'create'
            return
        }

        myMessage.save flush:true

        badgatorService.serviceMethod(myMessage.getUserId()) // check badges

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'myMessage.label', default: 'MyMessage'), myMessage.id])
                redirect myMessage
            }
            '*' { respond myMessage, [status: CREATED] }
        }
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def edit(MyMessage myMessage) {
        respond myMessage
    }

    @Transactional
    def update(MyMessage myMessage) {
        if (myMessage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (myMessage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond myMessage.errors, view:'edit'
            return
        }

        myMessage.save flush:true
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'myMessage.label', default: 'MyMessage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /**
     * Modify the value of a message for gamification
     * @return
     */
    @Secured(['ROLE_USER','ROLE_ADMIN'])
    @Transactional
    def vote(){
        MyMessage item = MyMessage.get(params.mId as Integer)
        item.value += (params.inc as Integer)
        update(item)

        badgatorService.serviceMethod(item.getUserId()) // check badges

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'answerMessage.label', default: 'Question'), item.id])
                redirect controller:"question", action:"show", id:params.qId, method:"GET"
            }
            '*' { respond item, [status: CREATED] }
        }
    }
}
