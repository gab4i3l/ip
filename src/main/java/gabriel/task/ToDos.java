package gabriel.task;

/**
 * Represents a todo task
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class ToDos extends Task {

    /**
     * Constructs a new ToDos task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDos task with a description and status.
     *
     * @param description The description of the todo task.
     * @param isDone The status of the todo task.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return Formatted string representing the todo task.
     */
    @Override
    public String toString() {
        String result = "[T]" + super.toString();
        assert result.startsWith("[T]") && !result.isBlank() : "Result should start with [T]";
        return result;
    }

    /**
     * Format the todo task for storage in a text file.
     *
     * @return Formatted string for file writing.
     */
    @Override
    public String writeToFile() {
        String result = String.format("Todos | %d | %s",
                this.checkDone(), this.description);
        assert result.startsWith("Todos | ") : "Result should start with Todos";
        return result;
    }
}
