package gabriel;

import java.time.format.DateTimeParseException;

import gabriel.exception.GabrielException;
import gabriel.parser.Parser;
import gabriel.storage.Storage;
import gabriel.task.Deadlines;
import gabriel.task.Events;
import gabriel.task.Task;
import gabriel.task.TaskList;
import gabriel.task.ToDos;
import gabriel.ui.Ui;

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
     * Display welcome message to users to understand how to use the chatbot.
     *
     * @return Welcome message to use the chatbot.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Display help message to users to understand how to use the chatbot.
     *
     * @return Help message to use the chatbot.
     */
    public String getHelpMessage() {
        return ui.getHelpMessage();
    }

    /**
     * Display example message to users to understand how to use the chatbot.
     *
     * @return Example message to use the chatbot.
     */
    public String getExampleMessage() {
        return ui.getExampleMessage();
    }

    /**
     * Saves user task to allow autosave after a command.
     */
    public void save() {
        try {
            storage.saveTasks(taskList.getTasks());
        } catch (GabrielException e) {
            ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Display task loading message to users.
     *
     * @return Task loading message.
     */
    public String getLoadingMessage() {
        if (taskList.getSize() > 0) {
            return ui.getLoadedMessage();
        }
        return "";
    }

    /**
     * Lists all current task in the task list.
     *
     * @return Formatted list of tasks.
     */
    public String performListCommand() {
        return ui.listTaskItems(taskList.getTasks());
    }

    /**
     * Marks a given task based on index given.
     *
     * @param input The raw user input.
     * @return Task marked confirmation message.
     */
    public String performMarkCommand(String input) throws GabrielException {
        int markIndex = Parser.parseIndex(input);

        try {
            Task task = taskList.getTasks().get(markIndex);

            task.setDone();

            save();

            String confirmation = ui.getTaskMarkedMessage(task);
            return confirmation + "\n" + ui.formatTaskCount(taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new GabrielException("That task number doesn't exist. "
                    + "Type list to see tasks.");
        }
    }

    /**
     * Unmarks a given task based on index given.
     *
     * @param input The raw user input.
     * @return Task unmarked confirmation message.
     */
    public String performUnmarkCommand(String input) throws GabrielException {
        try {
            int unmarkIndex = Parser.parseIndex(input);
            Task task = taskList.getTasks().get(unmarkIndex);

            task.setUndone();

            save();

            String confirmation = ui.getTaskUnmarkedMessage(task);

            return confirmation + "\n" + ui.formatTaskCount(taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new GabrielException("That task number doesn't exist Type list to see tasks.");
        }
    }

    /**
     * Adds a todo task into the current task list.
     *
     * @param input The raw user input.
     * @return Task added confirmation message.
     */
    public String performToDoCommand(String input) throws GabrielException {
        String toDoDetails = Parser.parseToDo(input);
        ToDos newToDo = new ToDos(toDoDetails);
        taskList.addTask(newToDo);
        save();
        return ui.getTaskAddedMessage(newToDo, taskList.getSize());
    }

    /**
     * Adds a Deadline task into the current task list.
     *
     * @param input The raw user input.
     * @return Task added confirmation message.
     */
    public String performDeadlineCommand(String input) throws GabrielException {
        String[] deadlineDetails = Parser.parseDeadline(input);
        try {
            Deadlines newDeadLine = new Deadlines(deadlineDetails[0], deadlineDetails[1]);
            taskList.addTask(newDeadLine);
            save();
            return ui.getTaskAddedMessage(newDeadLine, taskList.getSize());
        } catch (DateTimeParseException e) {
            throw new GabrielException("I can't understand that date!"
                    + " Please use the format: dd/mm/yyyy HHmm");
        }
    }

    /**
     * Adds an Event task into the current task list.
     *
     * @param input The raw user input.
     * @return Task added confirmation message.
     */
    public String performEventCommand(String input) throws GabrielException {
        String[] eventDetails = Parser.parseEvent(input);

        try {
            Events newEvent = new Events(eventDetails[0], eventDetails[1], eventDetails[2]);
            taskList.addTask(newEvent);
            save();
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
     * @return Task deleted confirmation message.
     */
    public String performDeleteCommand(String input) throws GabrielException {
        try {
            int deleteIndex = Parser.parseIndex(input);
            Task removedTask = taskList.deleteTask(deleteIndex);
            save();
            return ui.getTaskDeletedMessage(removedTask, taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new GabrielException("That task number doesn't exist Type list to see tasks.");
        }
    }

    /**
     * Saves current task list into a file.
     *
     * @return Task saved confirmation message.
     */
    public String performSaveCommand() {
        try {
            storage.saveTasks(taskList.getTasks());
            return ui.getTaskSavedMessage();
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Finds tasks that matches the user's input.
     *
     * @param input The raw user input.
     * @return List of task with matching keyword.
     */
    public String performFindCommand(String input) {
        try {
            String keyword = Parser.parseFindKeyword(input);
            return taskList.findTasks(keyword);
        } catch (GabrielException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @return Corresponding message for operation done on the list of tasks.
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
                return performSaveCommand();
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





