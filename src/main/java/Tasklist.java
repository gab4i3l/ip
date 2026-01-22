import java.util.ArrayList;

public class Tasklist {
    public ArrayList<Task> tasks;
    public Tasklist() {
        this.tasks = new ArrayList<>();
    }
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    public int getSize() {
        return this.tasks.size();
    }
}
