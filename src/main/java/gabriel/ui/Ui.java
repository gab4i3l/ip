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
     *
     * @return Welcome message for the user.
     */
    public String getWelcomeMessage() {
        return "Psy psy duck duck psy psy psy psyduck!\n(Hello! I am Gabriel, and I am here to manage your tasks! \n"
                + "Enter a command or input 'help' to know more!)\n";
    }

    /**
     * Display loading message to user on chatbot start up.
     *
     * @return Loading message for the user.
     */
    public String getLoadedMessage() {
        return "Psy duck duck psy!\n(Loaded previous tasks successfully!" + "Use the command 'list' to see them!)";
    }

    /**
     * Display help message to user.
     *
     * @return Help message for the user.
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
     * Display example message to user.
     *
     * @return Example message for the user.
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
     * @return Exit message for the user.
     */
    public String getExitMessage() {
        return "Psyyyy duckkk!\n(Bye. Hope to see you again soon!)\n";
    }

    /**
     * Confirmation message to user that a task has been successfully marked in the task list.
     *
     * @param task The task to be marked.
     * @return Confirmation message that task is marked for the user.
     */
    public String getTaskMarkedMessage(Task task) {
        return "Psy duck psy psy duck duck!\n(OK, I've marked this task as done:) \n"
                + "[X] " + task.getDescription() + "\n";
    }

    /**
     * Confirmation message to user that a task has been successfully unmarked in the task list.
     *
     * @param task The task to be unmarked.
     * @return Confirmation message that task is unmarked for the user.
     */
    public String getTaskUnmarkedMessage(Task task) {
        return "Duck psy duck duck psy psy!\n(OK, I've unmarked this task as not done yet:) \n"
                + "[ ] " + task.getDescription() + "\n";
    }

    /**
     * Confirmation message to user that a task has been successfully added to the task list.
     *
     * @return Confirmation message that task is added for the user.
     */
    public String getTaskAddedMessage(Task task, int size) {
        return "Psyduck psyduck!\n(Got it. I've added this task:) \n" + "   "
                + task + "\n" + formatTaskCount(size);
    }

    /**
     * Confirmation message to user that a task has been successfully deleted to the task list.
     *
     * @return Confirmation message that task is deleted for the user.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return "Psy duck psy psy.\n(Noted. I've removed this task:\n " + task
                + formatTaskCount(size) + ")";
    }

    /**
     * Confirmation message to user that a task has been successfully saved to the task list.
     *
     * @return Confirmation message that task is saved for the user.
     */
    public String getTaskSavedMessage() {
        return "Psy duck psy duck!\n(Alright we have saved your tasks!)";
    }

    /**
     * Display a warning to user that an unrecognised command is entered.
     *
     * @return Wrong command message to user.
     */
    public String getWrongCommandMessage() {
        return "DUCK DUCK PSY!\n(That is not a proper command!)";
    }

    /**
     * Prints the error message.
     *
     * @param message The description of the error.
     * @return Error message to the user.
     */
    public String getErrorMessage(String message) {
        return message;
    }

    /**
     * Display task count to user.
     *
     * @return Task count message for the user.
     */
    public String formatTaskCount(int size) {
        return "Psy psy duck duck.\n(Now you have " + size + " tasks in your list.)";
    }

    /**
     * List all the current task in the list.
     *
     * @param myTask The current list of Tasks to be displayed.
     * @return Formatted string of list of task.
     */
    public String listTaskItems(ArrayList<Task> myTask) {
        StringBuilder sb = new StringBuilder("Psyduck duckpsy:\n(Here are the tasks in your list:)\n");
        int count = 1;
        for (Task task : myTask) {
            sb.append(count).append(".").append(task.toString()).append("\n");
            count++;
        }
        return sb.toString();
    }
}



