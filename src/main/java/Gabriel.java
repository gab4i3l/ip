import java.util.Scanner; //For user input

public class Gabriel {
    private Storage storage;
    private Tasklist taskList;
    private Ui ui;

    public Gabriel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new Tasklist(storage.loadFile());
    }

    public void run() {
        ui.printWelcomeMessage();
        Scanner myScanner = new Scanner(System.in);
        String input = "";
        while (myScanner.hasNextLine()) {
            input = myScanner.nextLine();
            String command = Parser.getCommand(input);
            String description;
            switch(command){
                case "bye":
                    ui.printExitMessage();
                    break;
                case "list":
                    ui.printIndentations();
                    ui.listTaskItems(taskList.getTasks());
                    ui.printIndentations();
                    ui.printTaskListCount(taskList);
                    break;
                case "mark":
                    try {
                        ui.printIndentations();
                        int markIndex = Parser.parseIndex(input);
                        taskList.getTasks().get(markIndex).setDone(true);
                        ui.printTaskListCount(taskList);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The number given is too big! You don't have that many tasks!");
                    } catch (NumberFormatException e) {
                        System.out.println("That is not a number! Give me an actual number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("I don't think that task exist...");
                    }
                    finally {
                        ui.printIndentations();
                    }
                    break;
                case "unmark":
                    try {
                        ui.printIndentations();
                        int unmarkIndex = Parser.parseIndex(input);
                        taskList.getTasks().get(unmarkIndex).setDone(false);
                        ui.printTaskListCount(taskList);
                    }catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The number given is too big! You don't have that many tasks!");
                    } catch (NumberFormatException e) {
                        System.out.println("That is not a number! Give me an actual number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("I don't think that task exist...");
                    }
                    finally {
                        ui.printIndentations();
                    }
                    break;
                case "todo":
                    try {
                        ui.printIndentations();
                        description = input.substring(5).trim();
                        if (description.isEmpty()){
                            throw new StringIndexOutOfBoundsException();
                        }
                        ToDos newToDo = new ToDos(description);
                        taskList.getTasks().add(newToDo);
                        System.out.println("Got it. I've added this task: \n" + "   " + newToDo.toString());
                        ui.printTaskListCount(taskList);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("The description given is empty!");
                    }
                    finally {
                        ui.printIndentations();
                    }
                    break;
                case "deadline":
                    try {
                        ui.printIndentations();
                        String[] deadlineParts = Parser.parseDeadline(input);
                        if (deadlineParts[0].trim().isEmpty()){
                            System.out.println("The description given is empty!");
                            break;
                        }
                        description = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        Deadlines newDeadLine = new Deadlines(description,by);
                        taskList.getTasks().add(newDeadLine);
                        System.out.println("Got it. I've added this task: \n" + "   " + newDeadLine.toString());
                        ui.printTaskListCount(taskList);
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("The description given is empty!");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The deadline given is empty!");
                    }
                    finally{
                        ui.printIndentations();
                    }
                    break;
                case "event":
                    try {
                        ui.printIndentations();
                        String[] eventDetails = Parser.parseEvent(input);
                        Events newEvent = new Events(eventDetails[0],eventDetails[1],eventDetails[2]);
                        taskList.getTasks().add(newEvent);
                        System.out.println("Got it. I've added this task: \n" + "   " + newEvent.toString());
                        ui.printTaskListCount(taskList);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    } finally{
                        ui.printIndentations();
                    }
                    break;
                case "delete":
                    try {
                        ui.printIndentations();
                        int deleteIndex = Parser.parseIndex(input);
                        Task removedTask = taskList.getTasks().remove(deleteIndex);
                        System.out.println("Noted. I've removed this task:\n " +
                                removedTask.toString());
                        ui.printIndentations();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The number given is too big! You don't have that many tasks!");
                    } catch (NumberFormatException e) {
                        System.out.println("That is not a number! Give me an actual number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("I don't think that task exist...");
                    }
                    finally {
                        ui.printIndentations();
                    }
                    break;
                case "save":
                    storage.saveTasks(taskList.getTasks());
                    ui.printIndentations();
                    System.out.println("Alright we have saved your tasks!");
                    ui.printIndentations();
                    break;
                default:
                    ui.printIndentations();
                    System.out.println("\n" + "That is not a proper command!\n");
                    ui.printIndentations();
                    break;
            }
        }
    }

    }

    public void main(String[] args) {
        new Gabriel("./data/tasks.txt").run();
    }


