import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends Task{
    protected LocalDateTime by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by,USER_INPUT_FORMAT);
    }

    public Deadlines(String description, boolean isDone, String by) {
        super(description,isDone);
        this.by = LocalDateTime.parse(by,FILE_FORMAT);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + by.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String writeToFile() {
        return String.format("Deadlines | %d | %s | by: %s", this.checkDone(), this.description, by.format(FILE_FORMAT));
    }
}
