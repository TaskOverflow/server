package ovh.garcon.tasko

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(BadgatorService)
@Mock([User,Badge,MyMessage,BadgatorService])
@TestMixin(DomainClassUnitTestMixin)
class BadgatorServiceSpec extends Specification {

    def badgeSuperman
    def badgeHercule
    def badgeOne

    User bob

    def setup() {
        service.transactionManager = getTransactionManager()
        badgeSuperman = new Badge(label: "Superman", description: "badges.superman.desc", value: 10, users: []).save(failOnError: true)
        badgeHercule = new Badge(label: "Hercule", description: "badges.hercule.desc", value: 4, users: []).save(failOnError: true)
        badgeOne = new Badge(label: "N°1", description: "badges.nbone.desc", value: 4, users: []).save(failOnError: true)

        bob = new User(username: 'bob', password: 'bob').save(flush: true)
        bob.save flush: true
    }

    def cleanup() {
    }

    void "test to get N²1 badge"() {
        when:
        bob.addToMessages(new MyMessage(value: 10, content: "c", date: new Date()))
        bob.save flush: true
        service.serviceMethod(bob.id)
        then:
        User.getAll().first().getUsername() == 'bob'
        User.getAll().first().badges?.size() == 1
        User.getAll().first().getReputation() == 14
    }

    void "test to get Hercule badge"() {
        when:
        bob.addToMessages(new MyMessage(value: 50, content: "b", date: new Date()))
        bob.save flush: true, failOnError: true
        service.serviceMethod(bob.id)
        then:
        User.getAll().first().getUsername() == 'bob'
        User.getAll().first().badges?.size() == 2
        User.getAll().first().getReputation() == 58
    }

    void "test to get Superman badge"() {
        when:
        bob.addToMessages(new MyMessage(value: 100, content: "a", date: new Date()))
        bob.save flush: true, failOnError: true
        service.serviceMethod(bob.id)
        then:
        User.getAll().first().getUsername() == 'bob'
        User.getAll().first().getReputation() == 118
        User.getAll().first().badges?.size() == 3
    }

    void "test to get no badge"() {
        when:
        service.serviceMethod(bob.id)
        then:
        User.getAll().first().getUsername() == 'bob'
        User.getAll().first().getReputation() == 0
        User.getAll().first().badges == null
    }
}
