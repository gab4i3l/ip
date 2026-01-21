import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static String Indentations = "\u2500".repeat(50);
    public Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcomeMessage() {
        System.out.println(Indentations);
        System.out.println("\n Hello! I am Gabriel!" +
                "What can I do for you? \n");
        System.out.println(Indentations);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public static void listTaskItems(ArrayList<Task> myTask) {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : myTask) {
            System.out.println(count + "." + task.toString());
            count++;
        }
    }
}


}
