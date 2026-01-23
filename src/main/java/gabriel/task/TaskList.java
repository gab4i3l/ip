package gabriel.task;

import java.util.ArrayList;

/**
 * Manages a collection of tasks.
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list initialized with a specific list of tasks.
     *
     * @param tasks The specified list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes and returns the deleted task at a specified index.
     *
     * @param index The index of the task to be removed.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds tasks that matches the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword){
        int count = 1;
        boolean isFound = false;

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)){
                if (!isFound) {
                    System.out.println("Here are the matching tasks in your list:");
                    isFound = true;
                }
                System.out.println(count + "." + task.toString());
                count++;
            }
        }

        if(!isFound) {
            System.out.println("Sorry we did not find any matching tasks." +
                    " Perhaps the task does not exist or you can try other keywords!");
        }
    }

    /**
     * Returns the current number of task in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }
}
