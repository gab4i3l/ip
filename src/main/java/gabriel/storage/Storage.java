package gabriel.storage;

import gabriel.ui.Ui;
import gabriel.task.Deadlines;
import gabriel.task.Events;
import gabriel.task.Task;
import gabriel.task.ToDos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of data for Gabriel chatbot.
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Storage {
    private String filePath;

    /**
     * Construct a Storage object with a specified file path.
     * @param filePath The filepath to the stored data
     */
    public Storage (String filePath){
        this.filePath = filePath;
    }

    /**
     * Loads the task list from a saved file
     * @return The previously saved list of task
     */
    public ArrayList<Task> loadFile() {
        File file = new File(this.filePath);
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                System.out.println("No previous data found. We are having a fresh start!");
                return loadedTasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\|");
                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                switch (taskType) {
                    case "Todos":
                        Task toDo = new ToDos(description, isDone);
                        loadedTasks.add(toDo);
                        break;
                    case "Gabriel.Deadlines":
                        Task deadline = new Deadlines(description, isDone, parts[3].trim().replace("by: ",", "));
                        loadedTasks.add(deadline);
                        break;
                    case "Event":
                        Task event = new Events(description, isDone, parts[3].trim().replace("from: ",", "), parts[4].trim().replace("by: ",", "));
                        loadedTasks.add(event);
                        break;
                }
            }
            scanner.close();
            System.out.println(Ui.Indentations);
            System.out.println("Loaded previous tasks successfully! Use the command 'list' to see them!");

        } catch (IOException e) {
            System.out.println("Error! We cannot open the file!");
        }
        return loadedTasks;
    }

    /**
     * Saves the given list of task to a local storage file.
     * @param myTask The list of task to be saved
     */
    public void saveTasks(ArrayList<Task> myTask) {
        File file =  new File(this.filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            boolean parentDirectoryCreated = parentDirectory.mkdir();
            if (parentDirectoryCreated) {
                System.out.println("Directory created!" +  parentDirectory.getPath());
            }
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task task: myTask) {
                writer.println(task.writeToFile());
                System.out.println("Saved: " + task.toString());
            }
        } catch (IOException e) {
            System.out.println("Ran into an error saving tasks");
            System.out.println(Ui.Indentations);
        }
    }
}
