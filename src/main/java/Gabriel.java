import java.util.Scanner; //For user input
import java.util.ArrayList;
public class Gabriel {
    static ArrayList<String> myItems = new ArrayList<>();
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
            if (input.equals("list")){
                System.out.println(Indentations);
                listItems(myItems);
                System.out.println(Indentations);
                continue;
            }
            myItems.add(input);
            System.out.println(Indentations + "\n" + "added: " + input + "\n" + Indentations);
        }
        String exitMessage = Indentations + "\n" + "Bye. Hope to see you again soon!\n"
                + Indentations;
        System.out.println(exitMessage);
    }
    public static void listItems(ArrayList<String>myItems){
        int count = 1;
        for (String item: myItems){
            System.out.println(count + ". " + item);
            count++;
        }
    }
}
