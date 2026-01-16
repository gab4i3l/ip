public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X": " ");
    }

    public void setDone(boolean isDone) {
        if (!isDone) {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    "[ ] " + getDescription());

        }
        else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done: \n" +
                    "[X] " + getDescription());
        }
    }
    public String getDescription() {
        return description;
    }
    public String toString() {
        return "[" + getStatusIcon()+ "] " + getDescription();
    }

}
