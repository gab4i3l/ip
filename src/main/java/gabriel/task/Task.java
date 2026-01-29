package gabriel.task;

import java.time.format.DateTimeFormatter;
/**
 * Represents a task
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Task {
    /** The specified format user will input date and time. */
    public static final DateTimeFormatter USER_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /** The specified format the date and time will be saved in file. */
    public static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** The specified format the date and time will be displayed in responses. */
    public static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /** The description of the task. */
    protected String description;

    /** The status of the task. */
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the specified description and status.
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a visual representation of the task's completion status.
     * @return "X" if the task is completed, or a blank space " "  if it is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the completion status of the task and prints a confirmation message.
     * @param isDone Used to determine to mark the task as completed or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = true;
    }

    /**
     * Updates the completion status of the task and prints a confirmation message.
     * @param isDone Used to determine to mark the task as completed or not.
     */
    public void setUnDone(boolean isDone) {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return The task description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns an integer representation of task's completion status.
     * @return 1 if task is done, 0 otherwise.
     */
    public int checkDone() {
        if (this.isDone) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns a formatted string and status icon of the task.
     * @return Formatted string and status of task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Formats the task into string in specified format for file storage..
     * @return Formatted string for file writing.
     */
    public String writeToFile() {
        return String.format("Task | %d | %s",
                this.checkDone(), this.description);
    }

}
