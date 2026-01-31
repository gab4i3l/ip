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

    /** The user interface that interacts and responds to users. */
    private Ui ui;

    /**
     * Initializes a new Gabriel chatbot instance.
     *
     * @param filePath The filepath to the previously stored data.
     */
    public Gabriel(String filePath) {
        assert filePath != null : "filePath cannot be null";

        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadFile());

        assert ui != null : "ui cannot be null and should be initialised";
        assert storage != null : "storage cannot be null and should be initialised";
        assert taskList != null : "taskList cannot be null and should be initialised";
        assert taskList.getTasks() != null : "The task list encapsulated cannot be null";
    }

    /**
     * Returns the welcome message.
     *
     * @return the welcome message to greet the user.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Returns the help message.
     *
     * @return the help message to show user the possible commands.
     */
    public String getHelpMessage() {
        return ui.getHelpMessage();
    }

    /**
     * Returns the example message.
     *
     * @return the example message to show user an example of each command.
     */
    public String getExampleMessage() {
        return ui.getExampleMessage();
    }

    /**
     * Returns the loading message.
     *
     * @return the loading message to tell user the previous saved task is loaded.
     */
    public String getLoadingMessage() {
        if (taskList.getSize() > 0) {
            return "Loaded previous tasks successfully! Use the command 'list' to see them!";
        }
        return "";
    }

    /**
     * Lists all current tasks in the task list.
     *
     * @return the current list of tasks.
     */
    public String performListCommand() {
        return ui.listTaskItems(taskList.getTasks());
    }

    /**
     * Marks a given task based on index given.
     *
     * @param input The raw user input.
     * @return confirmation the selected task is marked.
     * @throws GabrielException If index is out of bounds or negative or if input is incomplete or incorrect format.
     */
    public String performMarkCommand(String input) throws GabrielException {
        int markIndex = Parser.parseIndex(input);
        assert markIndex >= 0 : "markIndex should be non-negative";
        assert markIndex < taskList.getSize() : "Index is out of bounds";

        Task task = taskList.getTasks().get(markIndex);

        task.setDone(true);
        assert task.getIsDone() : "Task staus should be set to done";

        String confirmation = "OK, I've marked this task as done: \n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
        return confirmation + "\n" + ui.getTaskCountMessage(taskList.getSize());
    }

    /**
     * Unmarks a given task based on index given.
     *
     * @param input The raw user input.
     * @return the confirmation message that the selected message is unmarked.
     * @throws GabrielException If index is out of bounds or negative or if input is incomplete or incorrect format.
     */
    public String performUnmarkCommand(String input) throws GabrielException {
        int unmarkIndex = Parser.parseIndex(input);
        assert unmarkIndex >= 0 : "unmarkIndex should be non-negative";
        assert unmarkIndex < taskList.getSize() : "Index is out of bounds";

        Task task = taskList.getTasks().get(unmarkIndex);

        task.setUnDone(false);
        assert !task.getIsDone() : "Task staus should be set to undone";

        String confirmation = "OK, I've unmarked this task as not done yet: \n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();

        return confirmation + "\n" + ui.getTaskCountMessage(taskList.getSize());
    }

    /**
     * Adds a todo task into the current task list.
     *
     * @param input The raw user input.
     * @return the confirmation message that the todo task is added.
     * @throws GabrielException If input is incomplete or incorrect format.
     */
    public String performToDoCommand(String input) throws GabrielException {
        String toDoDetails = Parser.parseToDo(input);
        int oldSize = taskList.getSize();
        ToDos newToDo = new ToDos(toDoDetails);
        taskList.addTask(newToDo);
        assert taskList.getSize() == oldSize + 1 : "Task list size should increase by 1";
        return ui.getTaskAddedMessage(newToDo, taskList.getSize());
    }

    /**
     * Adds a Deadline task into the current task list.
     *
     * @param input The raw user input.
     * @return the confirmation message that the deadline task is added.
     * @throws GabrielException If input is incomplete or incorrect format.
     */
    public String performDeadlineCommand(String input) throws GabrielException {
        String[] deadlineDetails = Parser.parseDeadline(input);
        try {
            Deadlines newDeadLine = new Deadlines(deadlineDetails[0], deadlineDetails[1]);
            int oldSize = taskList.getSize();
            taskList.addTask(newDeadLine);
            assert taskList.getSize() == oldSize + 1 : "Task list size should increase by 1";
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
     * @return the confirmation message that the event task is added.
     * @throws GabrielException If input is incomplete or incorrect format.
     */
    public String performEventCommand(String input) throws GabrielException {
        String[] eventDetails = Parser.parseEvent(input);

        try {
            Events newEvent = new Events(eventDetails[0], eventDetails[1], eventDetails[2]);
            int oldSize = taskList.getSize();
            taskList.addTask(newEvent);
            assert taskList.getSize() == oldSize + 1 : "Task list size should increase by 1";
            return ui.getTaskAddedMessage(newEvent, taskList.getSize());
        } catch (DateTimeParseException e) {
            throw new GabrielException("I can't understand that date!"
                    + " Please use the format: dd/mm/yyyy HHmm");
        }
    }

    /**
     * Deletes a task from the list based on given index.
     *
     * @param input The raw user input.
     * @return the confirmation message that the selected task is deleted.
     * @throws GabrielException If index given is out of bounds or negative or if input is incomplete or incorrect format.
     */
    public String performDeleteCommand(String input) throws GabrielException {
        int deleteIndex = Parser.parseIndex(input);
        assert deleteIndex >= 0 : "deleteIndex should be non-negative";
        assert deleteIndex < taskList.getSize() : "Index is out of bounds";
        int oldSize = taskList.getSize();
        Task removedTask = taskList.deleteTask(deleteIndex);
        assert taskList.getSize() == oldSize - 1 : "Task list size should decrease by 1";
        return ui.getTaskDeletedMessage(removedTask, taskList.getSize());
    }

    /**
     * Saves current task list into a file.
     *
     * @param input The raw user input (unused).
     * @return the confirmation message that the list of tasks is saved.
     */
    public String performSaveCommand(String input) {
        storage.saveTasks(taskList.getTasks());
        return ui.getTaskSavedMessage();
    }

    /**
     * Finds tasks that matches the user's input.
     *
     * @param input The raw user input.
     * @return the list of tasks that matches the keyword given.
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
     * @param input The raw user input.
     * @return the corresponding message for each command.
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





