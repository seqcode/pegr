package pegr;

public enum ProjectRole {
	OWNER("Owner"),
	GUEST("Guest"),
	PARTICIPANT("Participant")
	;
    
    private final String value;

    private ProjectRole(String value) { this.value = value; }

    @Override
    public String toString() { return value; } 
    
    public String getKey() { return value; }
}
