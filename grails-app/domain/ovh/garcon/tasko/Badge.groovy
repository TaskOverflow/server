package ovh.garcon.tasko

class Badge {

    String label
    String description
    Integer value
	
	static hasMany = [users: User]
    static belongsTo = User
	
    static constraints = {
        users nullable: true
    }
    
}
