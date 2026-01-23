package gabriel.ui;

import gabriel.task.Task;
import gabriel.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interface interactions and chatbot responses.
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Ui {
    /** Indentations for display purpose. */
    private static final String INDENTATIONS = "\u2500".repeat(50);

    /** To accept user inputs. */
    private Scanner scanner;

    /**
     * Initialize a new ui instance to accept user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the indentation line.
     */
    public void printIndentations() {
        System.out.println(INDENTATIONS);
    }

    /**
     * Display greeting message to user on chatbot start up.
     */
    public void printWelcomeMessage() {
        printIndentations();
        System.out.println("\n" + "Hello! I am Gabriel! " +
                "What can I do for you?\n");
        printIndentations();
    }

    /**
     * Display exit message to user on chatbot termination.
     */
    public void printExitMessage() {
        System.out.println(INDENTATIONS + "\n" +
                "Bye. Hope to see you again soon!\n" + INDENTATIONS);
    }

    /**
     * Confirmation message to user that a task has been successfully added to the task list.
     */
    public void printTaskAddedMessage(Task task){
        System.out.println("Got it. I've added this task: \n" + "   " + task.toString());
    }

    /**
     * Confirmation message to user that a task has been successfully deleted to the task list.
     */
    public void printTaskDeletedMessage(Task task){
        System.out.println("Noted. I've removed this task:\n " + task.toString());
    }

    /**
     * Confirmation message to user that a task has been successfully saved.
     */
    public void printTaskSavedMessage(){
        System.out.println("Alright we have saved your tasks!");
    }

    /**
     * Display a warning to user that an unrecognised command is entered.
     */
    public void printWrongCommandMessage(){
        System.out.println(INDENTATIONS + "\nThat is not a proper command!\n" + INDENTATIONS);
    }

    /**
     * Prints the error message.
     *
     * @param message The description of the error.
     */
    public void printErrorMessage(String message){
        System.out.println(message);
    }

    /**
     * List all the current task in the list.
     *
     * @param myTask The current list of Tasks to be displayed.
     */
    public void listTaskItems(ArrayList<Task> myTask) {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : myTask) {
            System.out.println(count + "." + task.toString());
            count++;
        }
    }

    /**
     * Display the current count of tasks in the task list.
     *
     * @param myTaskList The task list container to retrieve size from.
     */
    public void printTaskListCount(TaskList myTaskList) {
        System.out.println("Now you have " + myTaskList.getSize() + " tasks in your list.");
    }
}



