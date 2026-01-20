public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadlines(String description, boolean isDone, String by) {
        super(description,isDone);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (" + by + ")";
    }

    @Override
    public String writeToFile() {
        return String.format("Deadlines | %d | %s | by: %s", this.checkDone(), this.description, this.by);
    }
}
