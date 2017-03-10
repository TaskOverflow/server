package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

/**
 * Represent a 'subject' = question + answers
 */
class Question {

	/**
	 * Label of the question
	 */
	String title
	/**
	 * Message of the question
	 */
	QuestionMessage question
	static hasMany = [answers: AnswerMessage, tags: Tag]
	/**
	 * Answers given to this question
	 */
    Set<AnswerMessage> answers
	/**
	 * User who has asked
	 */
	static hasOne = [user: User]
	/**
	 * Define if the question is solved
	 */
	Boolean isSolved

    static constraints = {
		answers nullable: true
		tags nullable: true
		user nullable: true
    }

	static mapping = {
		answers sort: "value"
	}

	/**
	 * List of answers sort by value (for reputation)
	 * @return Set<AnswerMessage>
	 */
    Set<AnswerMessage> getAnswers() {
        return answers?.sort{it.value}?.reverse(true)
    }

	int getValue() {
		return question.getValue()
	}
}
