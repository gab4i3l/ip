package gabriel.task;

/**
 * Represents a todo task
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class ToDos extends Task {

    /**
     * Constructs a new ToDos task with the specified description.
     * @param description The description of the todo task
     */
    public ToDos(String description){
        super(description);
    }

    /**
     * Constructs a new ToDos task with a description and status.
     * @param description The description of the todo task
     * @param isDone The status of the todo task
     */
    public ToDos(String description, boolean isDone){
        super(description,isDone);
    }

    /**
     * Returns the string representation of the todo task
     * @return Formatted string representing the todo task
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * Format the todo task for storage in a text file.
     * @return Formatted string for file writing.
     */
    @Override
    public String writeToFile() {
        return String.format("Todos | %d | %s", this.checkDone(), this.description);
    }
}
