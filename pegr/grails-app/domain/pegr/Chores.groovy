package pegr

class Chores {
	String name
    String value
    static constraints = {
		name unique: true
        value nullable: true, blank: true
    }
}