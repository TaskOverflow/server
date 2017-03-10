package ovh.garcon.tasko

class AnswerMessage extends MyMessage {
	
	static belongsTo = [question: Question]
	static hasMany = [coms: ComMessage]
	
    static constraints = {
        coms nullable: true
    }
}
