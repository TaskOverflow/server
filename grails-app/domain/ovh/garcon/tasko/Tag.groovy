package ovh.garcon.tasko

class Tag {

    String label

    static hasMany = [questions: Question]

    static constraints = {
        questions nullable: true
        label unique: true
    }
}
