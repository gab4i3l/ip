package gabriel.ui;

import gabriel.task.Task;
import gabriel.task.Tasklist;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static String Indentations = "\u2500".repeat(50);
    public Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printIndentations() {
        System.out.println(Indentations);
    }

    public void printWelcomeMessage() {
        printIndentations();
        System.out.println("\n" + "Hello! I am Gabriel! " +
                "What can I do for you?\n");
        printIndentations();
    }

    public void printExitMessage() {
        System.out.println(Indentations + "\n" + "Bye. Hope to see you again soon!\n"
                + Indentations);
    }

    public void printTaskAddedMessage(Task task){
        System.out.println("Got it. I've added this task: \n" + "   " + task.toString());
    }

    public void printTaskDeletedMessage(Task task){
        System.out.println("Noted. I've removed this task:\n " + task.toString());
    }

    public void printTaskSavedMessage(){
        System.out.println("Alright we have saved your tasks!");
    }

    public void printWrongCommandMessage(){
        System.out.println("\nThat is not a proper command!\n");
    }

    public void printErrorMessage(String message){
        System.out.println(message);
    }

    public void listTaskItems(ArrayList<Task> myTask) {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : myTask) {
            System.out.println(count + "." + task.toString());
            count++;
        }
    }
    public  void printTaskListCount(Tasklist myTaskList) {
        System.out.println("Now you have " + myTaskList.getSize() + " tasks in your list.");
    }
}



