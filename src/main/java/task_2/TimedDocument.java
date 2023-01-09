package task_2;

public class TimedDocument implements Document {
    private final Document document;

    public TimedDocument(Document document) {
        this.document = document;
    }

    @Override
    public String getGcsPath() {
        return document.getGcsPath();
    }

    @Override
    public String parse() {
        long start = System.nanoTime();
        String message = document.parse();
        long time = System.nanoTime() - start;
        message += "\nTime taken to parse: " + (double) time / 1000_000_000.0 + " seconds";
        return message;
    }
}
