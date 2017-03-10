package ovh.garcon.tasko

class Profile {
    
    String firstname
    String lastname
    String email
    
    static belongsTo = [user: User]

    static constraints = {
        user nullable: true
        firstname nullable: true
        lastname nullable: true
        email nullable: true
    }

}
