package constants;

public enum RiskLevel {
	LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH"),
	DANGER("DANGER");

    private final String text;

    /**
     * @param text
     */
	RiskLevel(final String text) {
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
