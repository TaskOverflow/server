package ovh.garcon.tasko

class Question {

	String title
	QuestionMessage question
	static hasMany = [answers: AnswerMessage, tags: Tag]
	static belongsTo = Tag
	Boolean isSolved

    static constraints = {
		answers nullable: true
		tags nullable: true
    }

	int getValue() {
		int res = question.getValue()
		answers.each { c ->
			res += c.value
		}
		return res
	}
}
