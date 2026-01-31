package gabriel.ui;

import java.util.ArrayList;

import gabriel.task.Task;

/**
 * Handles all user interface interactions and chatbot responses.
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Ui {
    /**
     * Display welcome message to user on chatbot start up.
     **
     * @return the welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I am Gabriel, and I am here to manage your tasks! \n\n"
                + "Enter a command or input 'help' to know more!\n";
    }

    /**
     * Display help message to user on help command
     *
     * @return the help message.
     */
    public String getHelpMessage() {
        return "These are the available commands and their formats: \n"
                + "List current tasks: list\n"
                + "Mark a task as done: mark <index>\n"
                + "Unmark a task from done: unmark <index>\n"
                + "Add ToDo tasks: todo <task>\n"
                + "Add Deadlines tasks: deadline <task> /by dd/mm/yyyy HHmm\n"
                + "Add Events tasks: event <task> /by dd/mm/yyyy HHmm /from dd/mm/yyyy HHmm\n"
                + "Save current task list: save\n"
                + "Find a task: find <keyword>\n"
                + "Input 'example' to see some example commands!";
    }

    /**
     * Display example message to user on example command
     *
     * @return the example message.
     */
    public String getExampleMessage() {
        return "Here are some examples: \n"
                + "Mark a task as done: mark 1\n"
                + "Unmark a task from done: unmark 1\n"
                + "Add ToDo tasks: todo read books\n"
                + "Add Deadlines tasks: deadline return book /by 2/12/2019 1800\n"
                + "Add Events tasks: event project meeting /from 2/10/2019 1800 /to 2/10/2019 1900\n"
                + "Find a task: find book\n";
    }

    /**
     * Display exit message to user on chatbot termination.
     *
     * @return the exit message on exit command
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Display number of task.
     *
     * @param size the size of the task list.
     * @return the number of task message.
     */
    public String getTaskCountMessage(int size) {
        assert size >= 0 : "Task list size cannot be negative";
        String message = "Now you have " + size + " tasks in your list.";
        assert message.startsWith("Now you have ") : "Message should start with Now you have ";
        return message;
    }

    /**
     * Confirmation message to user that a task has been successfully added to the task list.* @param task the current list of tasks.
     *
     * @param task the task to be added.
     * @param size the size of the task list.
     * @return the confirmation message when a task is added.
     */
    public String getTaskAddedMessage(Task task, int size) {
        assert task != null : "Task cannot be null";
        assert size >= 0 : "Task list size cannot be negative";
        String message = "Got it. I've added this task: \n" + "   "
                + task.toString() + "\n" + "Now you have " + size + " tasks in your list.";
        assert message != null : "Message cannot be null";
        return message;
    }

    /**
     * Confirmation message to user that a task has been successfully deleted to the task list.
     *
     * @param task the task to be deleted.
     * @param size the size of the task list.
     * @return the confirmation message when a task is deleted.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        assert task != null : "Task cannot be null";
        assert size >= 0 : "Task list size cannot be negative";
        String message = "Noted. I've removed this task:\n " + task.toString()
                + "\nNow you have " + size + " tasks in your list.";
        assert message != null : "Message cannot be null";
        return message;
    }

    /**
     * Confirmation message to user that a task has been successfully saved.
     *
     * @return the confirmation message when a task is saved.
     */
    public String getTaskSavedMessage() {
        return "Alright we have saved your tasks!";
    }

    /**
     * Display a warning to user that an unrecognised command is entered.
     *
     * @return the error message when an improper command is given.
     */
    public String getWrongCommandMessage() {
        return "That is not a proper command!";
    }

    /**
     * Prints the error message.
     *
     * @param message The description of the error.
     * @return the error message when an exception occurred.
     */
    public String getErrorMessage(String message) {
        assert message != null : "Message cannot be null";
        return message;
    }

    /**
     * List all the current task in the list.
     *
     * @param myTask The current list of Tasks to be displayed.
     * @return the current list of tasks.
     */
    public String listTaskItems(ArrayList<Task> myTask) {
        assert myTask != null : "Tasklist cannot be null";

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : myTask) {
            sb.append(count).append(".").append(task.toString()).append("\n");
            count++;
        }
        String result = sb.toString();
        assert result != null : "Result cannot be null";
        return result;
    }
}



