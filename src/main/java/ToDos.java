public class ToDos extends Task{

    public ToDos(String description){
        super(description);
        System.out.println("Got it. I've added this task: \n" + "   " +
                this.toString());
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
