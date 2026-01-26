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

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public String getLoadingMessage() {
        if (taskList.getSize() > 0) {
            return "Loaded previous tasks successfully! Use the command 'list' to see them!";
        }
        return "";
    }

    /**
     * Lists all current task in the task list.
     */
    public String performListCommand(){
        return ui.listTaskItems(taskList.getTasks());
    }

    /**
     * Marks a given task based on index given.
     *
     * @param input The raw user input
     */
    public String performMarkCommand(String input) {
        try {

            int markIndex = Parser.parseIndex(input);
            taskList.getTasks().get(markIndex).setDone(true);
            return ui.listTaskItems(taskList.getTasks());
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Unmarks a given task based on index given.
     *
     * @param input The raw user input.
     */
    public String performUnmarkCommand(String input) {
        try {
            int unmarkIndex = Parser.parseIndex(input);
            taskList.getTasks().get(unmarkIndex).setDone(false);
            return ui.listTaskItems(taskList.getTasks());
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a todo task into the current task list.
     *
     * @param input The raw user input.
     */
    public String performToDoCommand(String input) {
        try {
            String toDoDetails = Parser.parseToDo(input);
            ToDos newToDo = new ToDos(toDoDetails);
            taskList.addTask(newToDo);
            return ui.getTaskAddedMessage(newToDo,taskList.getSize());
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a Deadline task into the current task list.
     *
     * @param input The raw user input.
     */
    public String performDeadlineCommand(String input){
        try {
            String[] deadlineDetails = Parser.parseDeadline(input);
            Deadlines newDeadLine = new Deadlines(deadlineDetails[0],deadlineDetails[1]);
            taskList.addTask(newDeadLine);
            return ui.getTaskAddedMessage(newDeadLine,taskList.getSize());
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a Event task into the current task list.
     *
     * @param input The raw user input.
     */
    public String performEventCommand(String input){
        try {
            String[] eventDetails = Parser.parseEvent(input);
            Events newEvent = new Events(eventDetails[0],eventDetails[1],eventDetails[2]);
            taskList.addTask(newEvent);
            return ui.getTaskAddedMessage(newEvent,taskList.getSize());
        } catch (GabrielException e){
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Deletes a task from the list based on given index.
     *
     * @param input The raw user input.
     */
    public String performDeleteCommand(String input){
        try {
            int deleteIndex = Parser.parseIndex(input);
            Task removedTask = taskList.deleteTask(deleteIndex);
            return ui.getTaskDeletedMessage(removedTask,taskList.getSize());
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Saves current task list into a file.
     *
     * @param input The raw user input.
     */
    public String performSaveCommand(String input){
        storage.saveTasks(taskList.getTasks());
        return ui.getTaskSavedMessage();
    }

    /**
     * Finds tasks that matches the user's input.
     *
     * @param input The raw user input.
     */
    public String performFindCommand(String input){
        try {
            String keyword = Parser.parseFindKeyword(input);
            return taskList.findTasks(keyword);
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String command = Parser.getCommand(input);
        switch(command){
            case "bye":
                return ui.getExitMessage();
            case "list":
                return performListCommand();
            case "mark":
                return performMarkCommand(input);
            case "unmark":
                return performUnmarkCommand(input);
            case "todo":
                return performToDoCommand(input);
            case "deadline":
                return performDeadlineCommand(input);
            case "event":
                return performEventCommand(input);
            case "delete":
                return performDeleteCommand(input);
            case "save":
                return performSaveCommand(input);
            case "find":
                return performFindCommand(input);
            default:
                return ui.getWrongCommandMessage();
        }
    }
}





