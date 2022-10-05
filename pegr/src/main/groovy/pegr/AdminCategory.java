package pegr;

public enum AdminCategory {
	BIO_SPECIFICATIONS("Biological Specifications"),
	PROTOCOLS("Protocol"),
    REPLICATES("Replicates"),
	ALIGNMENT_ANALYSIS("Alignment and Analysis"),
	OTHER("Other")
	;
	
	private final String text;

    private AdminCategory(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}