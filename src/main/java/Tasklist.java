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
        return tasks.remove(index);
    }
    public Task  getTask(int index) {
        return tasks.get(index);
    }
    public int getSize() {
        return tasks.size();
    }
}
