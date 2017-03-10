package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Class to represent users
 */
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    /**
     * Serial UID
     */
	private static final long serialVersionUID = 1

    /**
     * Definition for security service
     */
	transient springSecurityService

    /**
     * Name of the user
     */
	String username

    /**
     * Password of the user
     */
    String password

    /**
     * Define if the account is activated
     */
    boolean enabled = true

    /**
     * Define if the account has expired
     */
    boolean accountExpired

    /**
     * Define if the account is locked
     */
	boolean accountLocked

    /**
     * Define if the password has expired
     */
	boolean passwordExpired

	static hasMany = [messages: MyMessage, badges: Badge, questions: Question]

    /**
     * Questions asked by this user
     */
    Set<Question> questions
    static hasOne = [profil: Profile]

    /**
     * Get security roles of the user
     * @return
     */
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

    /**
     * Secure the password storage
     */
	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

    /**
     * Compute the reputation of the user
     * @return reputation as integer
     */
	Integer getReputation() {
		Integer rep = 0
		for(MyMessage m : messages)
			rep += m.getValue()
		for(Badge m : badges)
			rep += m.getValue()
		return rep
	}

    /**
     * Get the list of questions sorted by reputation
     * @return Set of Questions
     */
    Set<Question> getQuestions() {
        return questions?.sort{it.getValue()}?.reverse(true)
    }

	static transients = ['springSecurityService']

	static constraints = {
        username blank: false, unique: true
		password blank: false, password: true
		messages nullable: true, display: false, editable: false
		badges nullable: true, display: false, editable: false
		questions nullable: true, display: false, editable: false
		profil nullable: true, display: false, editable: false
		accountExpired display: false, editable: false
		accountLocked display: false, editable: false
		passwordExpired display: false, editable: false
		enabled display: false, editable: false
	}

	static mapping = {
		password column: '`password`'
        messages sort: "value", order: "desc"
	}
}
