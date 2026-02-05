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
     * Display greeting message to user on chatbot start up.
     */
    public String getWelcomeMessage() {
        return "Hello! I am Gabriel, and I am here to manage your tasks! \n\n"
                + "Enter a command or input 'help' to know more!\n";
    }

    public String getLoadedMessage() {
        return "Loaded previous tasks successfully! Use the command 'list' to see them!";
    }


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
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String getTaskCountMessage(Task task, int size) {
        return "Now you have " + size + " tasks in your list.";
    }

    /**
     * Confirmation message to user that a task has been successfully added to the task list.
     */
    public String getTaskAddedMessage(Task task, int size) {
        return "Got it. I've added this task: \n" + "   "
                + task.toString() + "\n" + "Now you have " + size + " tasks in your list.";
    }

    /**
     * Confirmation message to user that a task has been successfully deleted to the task list.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return "Noted. I've removed this task:\n " + task.toString()
                + "Now you have " + size + " tasks in your list.";
    }

    /**
     * Confirmation message to user that a task has been successfully saved.
     */
    public String getTaskSavedMessage() {
        return "Alright we have saved your tasks!";
    }

    /**
     * Display a warning to user that an unrecognised command is entered.
     */
    public String getWrongCommandMessage() {
        return "That is not a proper command!";
    }

    /**
     * Prints the error message.
     *
     * @param message The description of the error.
     */
    public String getErrorMessage(String message) {
        return message;
    }

    /**
     * List all the current task in the list.
     *
     * @param myTask The current list of Tasks to be displayed.
     */
    public String listTaskItems(ArrayList<Task> myTask) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : myTask) {
            sb.append(count).append(".").append(task.toString()).append("\n");
            count++;
        }
        return sb.toString();
    }
}



