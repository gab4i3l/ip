package gabriel.task;

import java.time.LocalDateTime;

public class Deadlines extends Task {
    protected LocalDateTime by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.USER_INPUT_FORMAT);
    }

    public Deadlines(String description, boolean isDone, String by) {
        super(description,isDone);
        this.by = LocalDateTime.parse(by, Task.FILE_FORMAT);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + by.format(Task.DISPLAY_FORMAT) + ")";
    }

    @Override
    public String writeToFile() {
        return String.format("Gabriel.Deadlines | %d | %s | by: %s", this.checkDone(), this.description, by.format(Task.FILE_FORMAT));
    }
}
