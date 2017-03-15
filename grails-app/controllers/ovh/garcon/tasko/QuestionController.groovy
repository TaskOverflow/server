package ovh.garcon.tasko

import grails.web.Controller

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import ovh.garcon.tasko.BadgatorService

/**
 * Manage questions
 */
@Transactional(readOnly = true)
@Controller
class QuestionController {

    /**
     * Gamification service
     */
    def badgatorService

    static responseFormats = ['json', 'xml']

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Question.list(params)?.sort{it.getValue()}?.reverse(true), model:[questionCount: Question.count()]
    }

    def healthcheck() {
        response.status = 200
    }

    def show(Question question) {
        respond question
    }

    def create() {
        respond new Question(question: new QuestionMessage(), date: new Date())
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    @Transactional
    def save(Question question) {

        def user = User.findByUsername(params.username)

        def qm = new QuestionMessage(
                user: user,
                content: params.content,
                date: new Date(),
                value: 0
        )

        def QUE = new Question(
                user: user,
                question: qm,
                isSolved: false,
                tags: question.tags,
                title: question.title
        ).save(flush:true)

        badgatorService.serviceMethod(user.id) // check badges

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), QUE.id])
                redirect QUE
            }
            '*' { respond QUE, [status: CREATED] }
        }
    }

    //@Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def edit(Question question) {
        respond question
    }

    @Transactional
    def update(Question question) {
        if (question == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (question.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond question.errors, view:'edit'
            return
        }

        question.save flush:true
    }

    @Transactional
    def delete(Question question) {

        if (question == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        question.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Secured(['ROLE_USER','ROLE_ADMIN'])
    @Transactional
    def solve(){
        Question item = Question.get(params.qId as Integer)
        print(params.qId as Integer)
        item.setIsSolved(true)

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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), item.id])
                redirect controller: "question", action: "show", id: params.qId
            }
            '*'{ respond item, [status: OK] }
        }
    }
}
