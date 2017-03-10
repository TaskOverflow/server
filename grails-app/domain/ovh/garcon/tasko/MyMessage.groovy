package ovh.garcon.tasko

class MyMessage {

	static belongsTo = [user: User]
	String content
	Date date
    int value

    static constraints = {
        user nullable: true
    }

    static mapping = {
        sort value: "desc"
        content type: "text"
    }
}
