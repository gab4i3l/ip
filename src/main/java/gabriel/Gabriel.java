package gabriel;

import gabriel.exception.GabrielException;
import gabriel.parser.Parser;
import gabriel.storage.Storage;
import gabriel.task.Deadlines;
import gabriel.task.Events;
import gabriel.task.Task;
import gabriel.task.TaskList;
import gabriel.task.ToDos;
import gabriel.ui.Ui;

import java.util.Scanner;

/**
 * Main entry point for Gabriel chatbot.
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Gabriel {
    /** The storage class that is responsible for loading and saving tasks. */
    private Storage storage;

    /** List that manages and stores all tasks when chatbot is running. */
    private TaskList taskList;

    /** The user interface that interacts and respond to users. */
    private Ui ui;

    /**
     * Initialize a new Gabriel chatbot instance.
     *
     * @param filePath The filepath to the previously stored data.
     */
    public Gabriel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadFile());
    }

    /**
     * Starts the main program.
     */
    public void run(){
        ui.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            String command = Parser.getCommand(input);

            switch(command){
            case "bye":
                ui.printExitMessage();
                break;
            case "list":
                performListCommand();
                break;
            case "mark":
                performMarkCommand(input);
                break;
            case "unmark":
                performUnmarkCommand(input);
                break;
            case "todo":
                performToDoCommand(input);
                break;
            case "deadline":
                performDeadlineCommand(input);
                break;
            case "event":
                performEventCommand(input);
                break;
            case "delete":
                performDeleteCommand(input);
                break;
            case "save":
                performSaveCommand(input);
                break;
            case "find":
                performFindCommand(input);
                break;
            default:
                ui.printWrongCommandMessage();
                break;
            }
        }
    }

    /**
     * Lists all current task in the task list.
     */
    public void performListCommand(){
        ui.printIndentations();
        ui.listTaskItems(taskList.getTasks());
        ui.printIndentations();
        ui.printTaskListCount(taskList);
    }

    /**
     * Marks a given task based on index given.
     *
     * @param input The raw user input
     */
    public void performMarkCommand(String input) {
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
    }

    /**
     * Unmarks a given task based on index given.
     *
     * @param input The raw user input.
     */
    public void performUnmarkCommand(String input) {
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
    }

    /**
     * Adds a todo task into the current task list.
     *
     * @param input The raw user input.
     */
    public void performToDoCommand(String input) {
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
    }

    /**
     * Adds a Deadline task into the current task list.
     *
     * @param input The raw user input.
     */
    public void performDeadlineCommand(String input){
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
    }

    /**
     * Adds a Event task into the current task list.
     *
     * @param input The raw user input.
     */
    public void performEventCommand(String input){
        try {
            ui.printIndentations();
            String[] eventDetails = Parser.parseEvent(input);
            Events newEvent = new Events(eventDetails[0],eventDetails[1],eventDetails[2]);
            taskList.addTask(newEvent);
            ui.printTaskAddedMessage(newEvent);
            ui.printTaskListCount(taskList);
        } catch (GabrielException e){
            ui.printErrorMessage(e.getMessage());
        } finally{
            ui.printIndentations();
        }
    }

    /**
     * Deletes a task from the list based on given index.
     *
     * @param input The raw user input.
     */
    public void performDeleteCommand(String input){
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
    }

    /**
     * Saves current task list into a file.
     *
     * @param input The raw user input.
     */
    public void performSaveCommand(String input){
        storage.saveTasks(taskList.getTasks());
        ui.printIndentations();
        ui.printTaskSavedMessage();
        ui.printIndentations();
    }

    /**
     * Finds tasks that matches the user's input.
     *
     * @param input The raw user input.
     */
    public void performFindCommand(String input){
        try {
            ui.printIndentations();
            String keyword = Parser.parseFindKeyword(input);
            taskList.findTasks(keyword);
            ui.printIndentations();
        } catch (GabrielException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Launches the Gabriel chatbot application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        new Gabriel("./data/Gabriel.txt").run();
    }
}





