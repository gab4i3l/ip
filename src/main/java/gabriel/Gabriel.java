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

import java.time.format.DateTimeParseException;

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

    public String getHelpMessage() {
        return ui.getHelpMessage();
    }

    public String getExampleMessage() {
        return ui.getExampleMessage();
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
    public String performListCommand() {
        return ui.listTaskItems(taskList.getTasks());
    }

    /**
     * Marks a given task based on index given.
     *
     * @param input The raw user input
     */
    public String performMarkCommand(String input) throws GabrielException {
        int markIndex = Parser.parseIndex(input);
        Task task = taskList.getTasks().get(markIndex);

        task.setDone(true);

        String confirmation = "OK, I've marked this task as done: \n"
                + "[ ] " + task.getDescription() + "\n";
        return confirmation + "\n" + ui.getTaskCountMessage(task, taskList.getSize());
    }

    /**
     * Unmarks a given task based on index given.
     *
     * @param input The raw user input.
     */
    public String performUnmarkCommand(String input) throws GabrielException {
        int unmarkIndex = Parser.parseIndex(input);
        Task task = taskList.getTasks().get(unmarkIndex);

        task.setUnDone(false);

        String confirmation =  "OK, I've unmarked this task as not done yet: \n"
                + "[ ] " + task.getDescription();

        return confirmation + "\n" + ui.getTaskCountMessage(task, taskList.getSize());
    }

    /**
     * Adds a todo task into the current task list.
     *
     * @param input The raw user input.
     */
    public String performToDoCommand(String input) throws GabrielException {
        String toDoDetails = Parser.parseToDo(input);
        ToDos newToDo = new ToDos(toDoDetails);
        taskList.addTask(newToDo);
        return ui.getTaskAddedMessage(newToDo, taskList.getSize());
    }

    /**
     * Adds a Deadline task into the current task list.
     *
     * @param input The raw user input.
     */
    public String performDeadlineCommand(String input) throws GabrielException {
        String[] deadlineDetails = Parser.parseDeadline(input);
        try {
            Deadlines newDeadLine = new Deadlines(deadlineDetails[0], deadlineDetails[1]);
            taskList.addTask(newDeadLine);
            return ui.getTaskAddedMessage(newDeadLine, taskList.getSize());
        } catch (DateTimeParseException e) {
            throw new GabrielException("I can't understand that date!"
                    + " Please use the format: dd/mm/yyyy HHmm");
        }
    }

    /**
     * Adds a Event task into the current task list.
     *
     * @param input The raw user input.
     */
    public String performEventCommand(String input) throws GabrielException {
        String[] eventDetails = Parser.parseEvent(input);

        try {
            Events newEvent = new Events(eventDetails[0], eventDetails[1], eventDetails[2]);
            taskList.addTask(newEvent);
            return ui.getTaskAddedMessage(newEvent, taskList.getSize());
        } catch (DateTimeParseException e) {
            throw new GabrielException("I can't understand that date!"
                    + " Please use the format: dd/mm/yyy HHmm");
        }
    }

    /**
     * Deletes a task from the list based on given index.
     *
     * @param input The raw user input.
     */
    public String performDeleteCommand(String input) throws GabrielException {
        int deleteIndex = Parser.parseIndex(input);
        Task removedTask = taskList.deleteTask(deleteIndex);
        return ui.getTaskDeletedMessage(removedTask, taskList.getSize());
    }

    /**
     * Saves current task list into a file.
     *
     * @param input The raw user input.
     */
    public String performSaveCommand (String input) {
        storage.saveTasks(taskList.getTasks());
        return ui.getTaskSavedMessage();
    }

    /**
     * Finds tasks that matches the user's input.
     *
     * @param input The raw user input.
     */
    public String performFindCommand (String input) {
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
        try {
            String command = Parser.getCommand(input);

            switch (command) {
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
                case "help":
                    return getHelpMessage();
                case "example":
                    return getExampleMessage();
                default:
                    return ui.getWrongCommandMessage();
            }
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.getErrorMessage("Something went wrong: " + e.getMessage());
        }
    }
}





