package Storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public String filepath;

    public Storage (String filePath){
        this.filepath = filePath;
    }

    public static void loadFile() {
        File file = new File(this.filePath);
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
