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
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isBlank() : "The description must not be null!";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the specified description and status.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        assert description != null && !description.isBlank() : "The description must not be null!";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     *
     * @return The task status string.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a visual representation of the task's completion status.
     *
     * @return "X" if the task is completed, or a blank space " "  if it is not completed.
     */
    public String getStatusIcon() {
        String icon = isDone ? "X" : " ";
        assert icon.equals("X") || icon.equals(" ") : "The icon can only be 'X' or ' ' ";
        return icon;
    }

    /**
     * Updates the completion status of the task and prints a confirmation message.
     *
     * @param isDone Used to determine to mark the task as completed or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = true;
        assert this.isDone : "Task should be marked done";
    }

    /**
     * Updates the completion status of the task and prints a confirmation message.
     *
     * @param isDone Used to determine to mark the task as completed or not.
     */
    public void setUnDone(boolean isDone) {
        this.isDone = false;
        assert !this.isDone : "Task should be marked undone";

    }

    /**
     * Returns an integer representation of task's completion status.
     *
     * @return 1 if task is done, 0 otherwise.
     */
    public int checkDone() {
        int value;
        if (this.isDone) {
            value = 1;
        } else {
            value = 0;
        }
        assert value == 0 || value == 1 : "The value must be 0 or 1";
        return value;
    }

    /**
     * Returns a formatted string and status icon of the task.
     *
     * @return Formatted string and status of task.
     */
    public String toString() {
        String result = "[" + getStatusIcon() + "] " + getDescription();
        assert result != null && result.isBlank() : "The result must not be blank or null!";
        return result;
    }

    /**
     * Formats the task into string in specified format for file storage.
     *
     * @return Formatted string for file writing.
     */
    public String writeToFile() {
        String result = String.format("Task | %d | %s",
                this.checkDone(), this.description);
        assert result != null && !result.isBlank() : "The result must not be blank or null!";
        return result;
    }

}
