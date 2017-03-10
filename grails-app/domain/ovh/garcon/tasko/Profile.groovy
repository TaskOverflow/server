package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

/**
 * Complementary information on a user
 */
class Profile {

    /**
     * firstname of the user
     */
    String firstname
    /**
     * Lastname of the user
     */
    String lastname
    /**
     * Email of the user
     */
    String email
    /**
     * URL of the user's image
     */
    String image
    
    static belongsTo = [user: User]

    static constraints = {
        user nullable: true, display: false, editable: false
        firstname nullable: true
        lastname nullable: true
        email nullable: true
        image nullable: true
    }

    String getImage() {
        if(image!=null)
            return image
        else
            return "0"
    }

}
