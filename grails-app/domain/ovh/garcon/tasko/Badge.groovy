package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

/**
 * Award for gamification
 */
class Badge implements Comparable {

    /**
     * Name of the badge
     */
    String label
    /**
     * How to get it
     */
    String description
    /**
     * Gain for gamification
     */
    Integer value

    /**
     * Users who have this badge
     */
	static hasMany = [users: User]
    static belongsTo = User
	
    static constraints = {
        users nullable: true
    }

    static mapping = {
        order: 'asc'
    }

    /**
     * In order to sort badge with a computed value
     * @param o
     * @return
     */
    @Override
    int compareTo(Object o) {
        int a = users != null ? users.size() : 0
        int b = ((Badge)o).getUsers() != null ? ((Badge)o).getUsers().size() : 0
        return a < b ? -1 : a > b ? 1 : 0
    }

}
