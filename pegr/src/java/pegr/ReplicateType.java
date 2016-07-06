package pegr;

public enum ReplicateType {
    BIOLOGICAL("Biological"), 
    TECHNICAL("Technical")
	;
    
    private final String value;

    private ReplicateType(String value) { this.value = value; }

    @Override
    public String toString() { return value; } 
    
    public String getKey() { return value; }
}