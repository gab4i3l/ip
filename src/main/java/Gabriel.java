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
                    } catch (GabrielException e) {
                        ui.printErrorMessage(e.getMessage());
                    } finally {
                        ui.printIndentations();
                    }
                    break;
                case "unmark":
                    try {
                        ui.printIndentations();
                        int unmarkIndex = Parser.parseIndex(input);
                        taskList.getTasks().get(unmarkIndex).setDone(false);
                        ui.printTaskListCount(taskList);
                    } catch (GabrielException e) {
                        ui.printErrorMessage(e.getMessage());
                    } finally {
                        ui.printIndentations();
                    }
                    break;
                case "todo":
                    try {
                        ui.printIndentations();
                        String toDoDetails = Parser.parseToDo(input);
                        ToDos newToDo = new ToDos(toDoDetails);
                        taskList.addTask(newToDo);
                        ui.printTaskAddedMessage(newToDo);
                        ui.printTaskListCount(taskList);
                    } catch (GabrielException e) {
                        ui.printErrorMessage(e.getMessage());
                    }
                    finally {
                        ui.printIndentations();
                    }
                    break;
                case "deadline":
                    try {
                        ui.printIndentations();
                        String[] deadlineDetails = Parser.parseDeadline(input);
                        Deadlines newDeadLine = new Deadlines(deadlineDetails[0],deadlineDetails[1]);
                        taskList.addTask(newDeadLine);
                        ui.printTaskAddedMessage(newDeadLine);
                        ui.printTaskListCount(taskList);
                    } catch (GabrielException e) {
                        ui.printErrorMessage(e.getMessage());
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
                        taskList.addTask(newEvent);
                        ui.printTaskAddedMessage(newEvent);
                        ui.printTaskListCount(taskList);
                    } catch (GabrielException e){
                        System.out.println(e.getMessage());
                    } finally{
                        ui.printIndentations();
                    }
                    break;
                case "delete":
                    try {
                        ui.printIndentations();
                        int deleteIndex = Parser.parseIndex(input);
                        Task removedTask = taskList.deleteTask(deleteIndex);
                        ui.printTaskDeletedMessage(removedTask);
                        ui.printIndentations();
                    } catch (GabrielException e) {
                        ui.printErrorMessage(e.getMessage());
                    } finally {
                        ui.printIndentations();
                    }
                    break;
                case "save":
                    storage.saveTasks(taskList.getTasks());
                    ui.printIndentations();
                    ui.printTaskSavedMessage();
                    ui.printIndentations();
                    break;
                default:
                    ui.printIndentations();
                    ui.printWrongCommandMessage();
                    ui.printIndentations();
                    break;
            }
        }
    }
}
public void main(String[] args) {
    new Gabriel("./data/Gabriel.txt").run();
}




