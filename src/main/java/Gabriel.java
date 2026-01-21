import java.util.Locale;
import java.util.Scanner; //For user input
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

public class Gabriel {
    static ArrayList<Task> myTask = new ArrayList<>();
    static String filePath = "./data/Gabriel.txt";
    public static void main(String[] args) {
        String Greetings = Indentations + "\n"
                + "Hello! I'm Gabriel! \n"
                + "What can I do for you? \n"
                + Indentations + "\n";

        loadFile();
        System.out.println(Greetings);
        System.out.println("Enter a command: \n");
        Scanner myScanner = new Scanner(System.in);
        String input = "";
        while (myScanner.hasNextLine()) {
            input = myScanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0].toLowerCase();
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
                        ToDos newToDo = new ToDos(description);
                        myTask.add(newToDo);
                        System.out.println("Got it. I've added this task: \n" + "   " + newToDo.toString());
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
                        Deadlines newDeadLine = new Deadlines(description,by);
                        myTask.add(newDeadLine);
                        System.out.println("Got it. I've added this task: \n" + "   " + newDeadLine.toString());
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
                        Events newEvent = new Events(eventDescription,from,to);
                        myTask.add(newEvent);
                        System.out.println("Got it. I've added this task: \n" + "   " + newEvent.toString());
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
                case "save":
                    saveTasks(myTask);
                    System.out.println(Indentations);
                    System.out.println("Alright we have saved your tasks!");
                    System.out.println(Indentations);
                    break;

                default:
                    System.out.println(Indentations + "\n" + "That is not a proper command!\n" + Indentations);
                    break;
            }
        }
    }


    public static void countTaskItems(){
        System.out.println("Now you have " + myTask.size() + " tasks in your list.");
    }

    public static void loadFile() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                System.out.println("No previous data found. We are having a fresh start!");
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\|");
                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                switch(taskType){
                    case "Todos":
                        Task toDo = new ToDos(description,isDone);
                        myTask.add(toDo);
                        break;
                    case "Deadlines":
                        Task deadline = new Deadlines(description,isDone,parts[3].trim());
                        myTask.add(deadline);
                        break;
                    case "Event":
                        Task event = new Events(description,isDone,parts[3].trim(), parts[4].trim());
                        myTask.add(event);
                        break;
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error! We cannot open the file!");
        }
        System.out.println(Indentations);
        System.out.println("Loaded previous tasks successfully! Use the command 'list' to see them!");

    }

    public static void saveTasks(ArrayList<Task> myTask) {
        File file =  new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            boolean parentDirectoryCreated = parentDirectory.mkdir();
            if (parentDirectoryCreated) {
                System.out.println("Directory created!" +  parentDirectory.getPath());
            }
        }
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (Task task: myTask) {
                writer.println(task.writeToFile());
                System.out.println("Saved: " + task.toString());
            }
        } catch (IOException e) {
            System.out.println("Ran into an error saving tasks");
            System.out.println(Indentations);
        }
    }

}
