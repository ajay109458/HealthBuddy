package constants;

public enum Genders {
	ALL("ALL"),
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String text;

    /**
     * @param text
     */
	Genders(final String text) {
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
