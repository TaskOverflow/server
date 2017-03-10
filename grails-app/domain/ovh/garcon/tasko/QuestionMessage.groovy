package ovh.garcon.tasko

class QuestionMessage extends MyMessage {
	
	static hasMany = [coms: ComMessage]
	
    static constraints = {
        coms nullable: true
    }

}
