import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Events(String description, String from, String to) {
        super(description);
        this.to = LocalDateTime.parse(to, USER_INPUT_FORMAT);
        this.from = LocalDateTime.parse(from, USER_INPUT_FORMAT);
    }

    public Events(String description, boolean isDone, String from, String to) {
        super(description,isDone);
        this.to = LocalDateTime.parse(to, FILE_FORMAT);
        this.from = LocalDateTime.parse(from, FILE_FORMAT);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from:" + from.format(DISPLAY_FORMAT)  + "to:" + to.format(DISPLAY_FORMAT) +  ")";
    }

    @Override
    public String writeToFile() {
        return String.format("Event | %d | %s | from: %s | to: %s", this.checkDone(), this.description,from.format(FILE_FORMAT),to.format(FILE_FORMAT));
    }
}
