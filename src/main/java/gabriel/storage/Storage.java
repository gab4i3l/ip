package gabriel.storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import gabriel.task.Deadlines;
import gabriel.task.Events;
import gabriel.task.Task;
import gabriel.task.ToDos;



/**
 * Handles loading and saving of data for Gabriel chatbot.
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Storage {
    /** Indentations for display purpose. */
    private static final String INDENTATIONS = "\u2500".repeat(50);

    /** The file path to load or save tasks. */
    private String filePath;

    /**
     * Construct a Storage object with a specified file path.
     *
     * @param filePath The filepath to the stored data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from a saved file.
     *
     * @return The previously saved list of task.
     */
    public ArrayList<Task> loadFile() {
        File file = new File(this.filePath);
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                return loadedTasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\s*\\|\\s*");
                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                switch (taskType) {
                case "Todos":
                    Task toDo = new ToDos(description, isDone);
                    loadedTasks.add(toDo);
                    break;
                case "Deadlines":
                    Task deadline = new Deadlines(description, isDone,
                            parts[3].trim().replace("by: ", ""));
                    loadedTasks.add(deadline);
                    break;
                case "Event":
                    Task event = new Events(description, isDone,
                            parts[3].trim().replace("from: ",""),
                            parts[4].trim().replace("to: ", ""));
                    loadedTasks.add(event);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type!");
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error! We cannot open the file!");
        }
        return loadedTasks;
    }

    /**
     * Saves the given list of task to a local storage file.
     *
     * @param myTask The list of task to be saved.
     */
    public void saveTasks(ArrayList<Task> myTask) {
        File file = new File(this.filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            boolean isParentDirectoryCreated = parentDirectory.mkdir();
            if (isParentDirectoryCreated) {
                System.out.println("Directory created!" + parentDirectory.getPath());
            }
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task task: myTask) {
                writer.println(task.writeToFile());
                System.out.println("Saved: " + task.toString());
            }
        } catch (IOException e) {
            System.out.println("Ran into an error saving tasks");
            System.out.println(INDENTATIONS);
        }
    }
}
