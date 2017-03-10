package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import grails.databinding.BindingFormat

/**
 * Generic class for messages
 */
class MyMessage {

	static belongsTo = [user: User, question: Question]
    /**
     * Text of the message
     */
	String content
    /**
     * Formatted date of publication
     */
    @BindingFormat('dd-mm-yyyy hh:mm')
	Date date
    /**
     * Value for gamification
     */
    int value
    /**
     * Question linked to this message
     */
    Question question

    static constraints = {
        user nullable: true
        question nullable: true
    }

    static mapping = {
        sort value: "desc"
        content type: "text"
    }

}
