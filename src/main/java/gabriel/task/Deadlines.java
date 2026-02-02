package gabriel.task;

import java.time.LocalDateTime;
/**
 * Class for Deadlines object
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Deadlines extends Task {
    /** The deadline of the deadline task. */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.USER_INPUT_FORMAT);
    }

    /**
     * Constructs a Deadline task with the specified description,status and deadline.
     *
     * @param description The description of the deadline task.
     * @param isDone The status of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadlines(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, Task.FILE_FORMAT);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Formatted string representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(Task.DISPLAY_FORMAT) + ")";
    }

    /**
     * Formats the deadline task for storage in a text file.
     *
     * @return Formatted string for file writing.
     */
    @Override
    public String writeToFile() {
        return String.format("Deadlines | %d | %s | by: %s",
                this.checkDone(), this.description, by.format(Task.FILE_FORMAT));
    }
}
