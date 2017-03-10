package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

/**
 * Specialized class for comment message
 */
class ComMessage extends MyMessage {

    /**
     * Message commented
     */
    MyMessage parentMessage

    static constraints = {
    }
}
