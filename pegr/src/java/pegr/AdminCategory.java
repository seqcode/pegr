package pegr;

public enum AdminCategory {
	BIO_MATERIAL("Biological Material"),
	PROTOCOLS("Protocol"),
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