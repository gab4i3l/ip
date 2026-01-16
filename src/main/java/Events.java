public class Events extends Task{
    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
        System.out.println("Got it. I've added this task: \n" + "   " +
                this.toString());
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
