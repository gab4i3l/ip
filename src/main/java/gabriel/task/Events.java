package gabriel.task;

import java.time.LocalDateTime;

public class Events extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Events(String description, String from, String to) {
        super(description);
        this.to = LocalDateTime.parse(to, Task.USER_INPUT_FORMAT);
        this.from = LocalDateTime.parse(from, Task.USER_INPUT_FORMAT);
    }

    public Events(String description, boolean isDone, String from, String to) {
        super(description,isDone);
        this.to = LocalDateTime.parse(to, Task.FILE_FORMAT);
        this.from = LocalDateTime.parse(from, Task.FILE_FORMAT);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from:" + from.format(Task.DISPLAY_FORMAT)  + "to:" + to.format(Task.DISPLAY_FORMAT) +  ")";
    }

    @Override
    public String writeToFile() {
        return String.format("Event | %d | %s | from: %s | to: %s", this.checkDone(), this.description,from.format(Task.FILE_FORMAT),to.format(Task.FILE_FORMAT));
    }
}
