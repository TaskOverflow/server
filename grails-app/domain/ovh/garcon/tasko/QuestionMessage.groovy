package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

/**
 * Class for specialized messages for questions
 */
class QuestionMessage extends MyMessage {
    /**
     * comments of the question
     */
	static hasMany = [coms: ComMessage]
	
    static constraints = {
        coms nullable: true
    }

}
