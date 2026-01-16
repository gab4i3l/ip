import java.util.Scanner; //For user input
import java.util.ArrayList;
public class Gabriel {
    static ArrayList<Task> myTask = new ArrayList<>();
    public static void main(String[] args) {
        String Indentations = "\u2500".repeat(50);
        String Greetings = Indentations + "\n"
                + "Hello! I'm Gabriel \n"
                + "What can I do for you? \n"
                + Indentations + "\n";

        System.out.println(Greetings);
        System.out.println("Enter something and I will echo it back to you: \n");
        Scanner myScanner = new Scanner(System.in);
        String input = "";
        while (myScanner.hasNextLine()) {
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
                    countTaskItems();
                    break;
                case "mark":
                    try {
                        System.out.println(Indentations);
                        int markIndex = Integer.parseInt(parts[1]) - 1;
                        myTask.get(markIndex).setDone(true);
                        countTaskItems();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The number given is too big! You don't have that many tasks!");
                    } catch (NumberFormatException e) {
                        System.out.println("That is not a number! Give me an actual number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("I don't think that task exist...");
                    }
                    finally {
                        System.out.println(Indentations);
                    }
                    break;
                case "unmark":
                    try {
                        System.out.println(Indentations);
                        int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                        myTask.get(unmarkIndex).setDone(false);
                        countTaskItems();
                    }catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The number given is too big! You don't have that many tasks!");
                    } catch (NumberFormatException e) {
                        System.out.println("That is not a number! Give me an actual number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("I don't think that task exist...");
                    }
                    finally {
                        System.out.println(Indentations);
                    }
                    break;
                case "todo":
                    try {
                        System.out.println(Indentations);
                        description = input.substring(5).trim();
                        if (description.isEmpty()){
                            throw new StringIndexOutOfBoundsException();
                        }
                        myTask.add(new ToDos(description));
                        countTaskItems();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("The description given is empty!");
                    }
                    finally {
                        System.out.println(Indentations);
                    }
                    break;
                case "deadline":
                    try {
                        System.out.println(Indentations);
                        String deadlineInput = input.substring(9).trim();
                        String[] deadlineParts = deadlineInput.split(" /by ");
                        if (deadlineParts[0].trim().isEmpty()){
                            System.out.println("The description given is empty!");
                            break;
                        }
                        description = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        myTask.add(new Deadlines(description, by));
                        countTaskItems();
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("The description given is empty!");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The deadline given is empty!");
                    }
                    finally{
                        System.out.println(Indentations);
                    }
                    break;
                case "event":
                    try {
                        System.out.println(Indentations);
                        String eventInput = input.substring(6).trim();
                        if (eventInput.startsWith("/from")) {
                            System.out.println("The description given is empty!");
                            break;
                        }
                        String[] eventParts = eventInput.split(" /from ");
                        String eventDescription = eventParts[0].trim();
                        String[] timeParts = eventParts[1].split(" /to ");
                        String from = timeParts[0].trim();
                        String to = timeParts[1].trim();
                        if (from.isEmpty() || to.isEmpty()) {
                            System.out.println("Please provide a valid from and to");
                        }
                        myTask.add(new Events(eventDescription, from, to));
                        countTaskItems();
                    } finally {
                        System.out.println(Indentations);
                    }
                    break;
                case "delete":
                    try {
                        System.out.println(Indentations);
                        int deleteIndex = Integer.parseInt(parts[1]) - 1;
                        Task removedTask = myTask.remove(deleteIndex);
                        System.out.println("Noted. I've removed this task:\n " +
                                removedTask.toString());
                        countTaskItems();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The number given is too big! You don't have that many tasks!");
                    } catch (NumberFormatException e) {
                        System.out.println("That is not a number! Give me an actual number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("I don't think that task exist...");
                    }
                    finally {
                        System.out.println(Indentations);
                    }
                    break;

                default:
                    System.out.println(Indentations + "\n" + "That is not a proper command!\n" + Indentations);
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
