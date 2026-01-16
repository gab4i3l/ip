import java.util.Scanner; //For user input
import java.util.ArrayList;
public class Gabriel {
    static ArrayList<Task> myTask = new ArrayList<>();
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
            String[] parts = input.split(" ");
            String command = parts[0];
            String description;

            switch(command){
                case "bye":
                    String exitMessage = Indentations + "\n" + "Bye. Hope to see you again soon!\n"
                            + Indentations;
                    System.out.println(exitMessage);
                    return;
                case "list":
                    System.out.println(Indentations);
                    listTaskItems(myTask);
                    System.out.println(Indentations);
                    break;
                case "mark":
                    System.out.println(Indentations);
                    int markIndex = Integer.parseInt(parts[1]) - 1;
                    myTask.get(markIndex).setDone(true);
                    System.out.println(Indentations);
                    break;
                case "unmark":
                    System.out.println(Indentations);
                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    myTask.get(unmarkIndex).setDone(false);
                    System.out.println(Indentations);
                    break;
                case "todo":
                    System.out.println(Indentations);
                    description = input.substring(5);
                    myTask.add(new ToDos(description));
                    countTaskItems();
                    System.out.println(Indentations);
                    break;
                case "deadline":
                    System.out.println(Indentations);
                    String deadlineInput = input.substring(9);
                    String[] deadlineParts = deadlineInput.split(" /by ");
                    description = deadlineParts[0];
                    String by = deadlineParts[1];
                    myTask.add(new Deadlines(description,by));
                    countTaskItems();
                    System.out.println(Indentations);
                    break;
                case "event":
                    System.out.println(Indentations);
                    String eventInput = input.substring(6);
                    String[] eventParts = eventInput.split(" /from ");
                    String eventDescription = eventParts[0];
                    String[] timeParts = eventParts[1].split(" /to ");
                    String from = timeParts[0];
                    String to = timeParts[1];
                    myTask.add(new Events(eventDescription,from,to));
                    countTaskItems();
                    System.out.println(Indentations);
                    break;
                default:
                    myTask.add(new Task(input));
                    System.out.println(Indentations + "\n" + "added: " + input + "\n" + Indentations);
                    break;
            }
        }
    }
    public static void listTaskItems(ArrayList<Task>myTask){
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task: myTask){
            System.out.println(count + "." + task.toString());
            count++;
        }
    }

    public static void countTaskItems(){
        System.out.println("Now you have " + myTask.size() + " tasks in your list.");
    }
}
