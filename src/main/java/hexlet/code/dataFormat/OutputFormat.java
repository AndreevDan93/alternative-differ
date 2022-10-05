package hexlet.code.dataFormat;

public enum OutputFormat {
    JSON("json"),
    PLAIN("plain"),
    STYLISH("stylish");

    private final String title;

    OutputFormat(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static OutputFormat getOutputFormat(String format) {
        for (OutputFormat outputFormat : OutputFormat.values()) {
            if (outputFormat.getTitle().equals(format)) {
                return outputFormat;
            }
        }
        return STYLISH;
    }

}
