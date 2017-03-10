package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

/**
 * Class for questions' tags
 */
class Tag {

    /**
     * Label of the tag
     */
    String label

    static hasMany = [questions: Question]
    static belongsTo = Question
    static constraints = {
        questions nullable: true
        label unique: true
    }

    @Override
    String toString() {
        return (label ?: "")
    }
}
