package gabriel.ui;

import gabriel.task.Task;

import java.util.ArrayList;

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
        return  "Hello! I am Gabriel! " + "What can I do for you?\n";
    }

    /**
     * Display exit message to user on chatbot termination.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Confirmation message to user that a task has been successfully added to the task list.
     */
    public String getTaskAddedMessage(Task task, int size){
        return "Got it. I've added this task: \n" + "   " + task.toString() +
                "Now you have " + size + " tasks in your list.";
    }

    /**
     * Confirmation message to user that a task has been successfully deleted to the task list.
     */
    public String getTaskDeletedMessage(Task task, int size){
        return "Noted. I've removed this task:\n " + task.toString() +
        "Now you have " + size + " tasks in your list.";
    }

    /**
     * Confirmation message to user that a task has been successfully saved.
     */
    public String getTaskSavedMessage(){
        return "Alright we have saved your tasks!";
    }

    /**
     * Display a warning to user that an unrecognised command is entered.
     */
    public String getWrongCommandMessage(){
        return "That is not a proper command!" ;
    }

    /**
     * Prints the error message.
     *
     * @param message The description of the error.
     */
    public String getErrorMessage(String message){
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



