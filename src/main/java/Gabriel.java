import java.util.Scanner; //For user input
public class Gabriel {
    public static void main(String[] args) {
        String Indentations = "--------------------";
        String Greetings = Indentations + "\n"
                + "Hello! I'm Gabriel \n"
                + "What can I do for you? \n"
                + Indentations + "\n";

        System.out.println(Greetings);
        System.out.println("Enter something and I will echo it back to you: \n");
        Scanner myScanner = new Scanner(System.in);
        String input = "";
        while (true) {
            input = myScanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(Indentations + "\n" + input + "\n" + Indentations);
        }
        String exitMessage = Indentations + "\n" + "Bye. Hope to see you again soon!\n"
                + Indentations;
        System.out.println(exitMessage);
    }
}
