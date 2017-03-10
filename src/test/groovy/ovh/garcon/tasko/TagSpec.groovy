package ovh.garcon.tasko

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Tag)
class TagSpec extends Specification {

    def badgeSuperman
    def badgeHercule
    def badgeOne
    def pro
    def question
    def com
    def answer
    def qm
    def bob

    def setup() {
        pro = new Profile(
                firstname: "William",
                lastname: "Adama",
                image: "www.gus.com/1.png",
                email: "admin@taskoverflow.com"
        )
        question = new Question(
                title: "Create a OOP language",
                question: null,
                tags: [],
                isSolved: false
        )
        com = new ComMessage(
                content: "I know.",
                date: new Date())
        answer = new AnswerMessage(
                content: "Perpetual sincerity out suspected necessary one but provision satisfied. Respect nothing use set waiting pursuit nay you looking. If on prevailed concluded ye abilities. Address say you new but minuter greater. Do denied agreed in innate. Can and middletons thoroughly themselves him. Tolerably sportsmen belonging in september no am immediate newspaper. Theirs expect dinner it pretty indeed having no of. Principle september she conveying did eat may extensive.",
                date: new Date(),
                value: 3
        )
        qm = new QuestionMessage(
                content: "How to do it quick? Help me please!",
                date: new Date(),
                value: 5)
        bob = new User(
                username: 'bob',
                password: 'bob',
                badges: badgeOne,
                enabled: true,
                messages: [],
                profil: pro)
    }

    def cleanup() {
    }

    void "test that the constructors are correct"() {
        when:
        badgeSuperman = new Badge(label: "Superman", description: "badges.superman.desc", value: 10, users: [])
        badgeHercule = new Badge(label: "Hercule", description: "badges.hercule.desc", value: 4, users: bob)
        badgeOne = new Badge()

        then:
        badgeSuperman.label == "Superman"
        badgeSuperman.description == "badges.superman.desc"
        badgeSuperman.value == 10
        badgeSuperman.users.size() == 0
        badgeHercule.users.size() == 1
        badgeOne.users == null
    }

    void "test compareTo"() {
        when:
        badgeSuperman = new Badge(label: "Superman", description: "badges.superman.desc", value: 10, users: [])
        badgeHercule = new Badge(label: "Hercule", description: "badges.hercule.desc", value: 4, users: bob)
        badgeOne = new Badge()

        then:
        badgeOne < badgeHercule
        badgeOne == badgeSuperman
        badgeSuperman < badgeHercule
    }
}
