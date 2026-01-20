public class Events extends Task{
    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    public Events(String description, boolean isDone, String from, String to) {
        super(description,isDone);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (" + this.from + this.to + ")";
    }

    @Override
    public String writeToFile() {
        return String.format("Event | %d | %s | from: %s | to: %s", this.checkDone(), this.description,this.from,this.to);
    }
}
