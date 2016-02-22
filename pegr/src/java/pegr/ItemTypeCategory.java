package pegr;

public enum ItemTypeCategory {
    ANTIBODY("Antibody"), 
    TRACED_SAMPLE("Traced Sample"),
    OTHER("Other")
	;
    
    private final String value;

    private ItemTypeCategory(String value) { this.value = value; }

    @Override
    public String toString() { return value; } 
    
    public String getKey() { return value; }
}
