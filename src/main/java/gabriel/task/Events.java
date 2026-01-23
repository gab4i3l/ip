package gabriel.task;

import java.time.LocalDateTime;

/**
 * Represents a task in the category of Event
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Events extends Task {
    /** The start time of the event. */
    protected LocalDateTime from;

    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs an Events task with the specified description and time range.
     *
     * @param description Description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.to = LocalDateTime.parse(to, Task.USER_INPUT_FORMAT);
        this.from = LocalDateTime.parse(from, Task.USER_INPUT_FORMAT);
    }

    /**
     * Constructs an Events task with the specified description,status and time range.
     *
     * @param description Description of the event.
     * @param isDone Status of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Events(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.to = LocalDateTime.parse(to, Task.FILE_FORMAT);
        this.from = LocalDateTime.parse(from, Task.FILE_FORMAT);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return Formatted string representing the event.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() +
                " (from:" + from.format(Task.DISPLAY_FORMAT) +
                "to:" + to.format(Task.DISPLAY_FORMAT) + ")";
    }

    /**
     * Format the event task for storage in a text file.
     *
     * @return Formatted string for file writing.
     */
    @Override
    public String writeToFile() {
        return String.format("Event | %d | %s | from: %s | to: %s",
                this.checkDone(), this.description, from.format(Task.FILE_FORMAT), to.format(Task.FILE_FORMAT));
    }
}
