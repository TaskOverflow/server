package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */


/**
 * Class for specialized messages for answers
 */
class AnswerMessage extends MyMessage {

    /**
     * Comments of the answer
     */
	static hasMany = [coms: ComMessage]
	
    static constraints = {
        coms nullable: true
    }

    static mapping = {
        coms sort: "date"
    }
}
