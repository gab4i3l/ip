public class Parser {
    public static String getCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].toLowerCase();
    }
    public static int parseIndex(String input){
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1])-1;
    }

    public static String[] parseDeadline (String input){
        String content = input.substring(9).trim();
        return content.split(" /by ");
    }

    public static String[] parseEvent(String input) throws Exception {
        String eventInput = input.substring(6).trim();
        if (eventInput.startsWith("/from")) {
            throw new Exception("The description of an event cannot be empty!");
        }
        String[] eventParts = eventInput.split(" /from ");
        String eventDescription = eventParts[0].trim();
        String[] timeParts = eventParts[1].split(" /to ");
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Please provide a valid from and to");
        }
        return new String[]{eventDescription,from,to};
    }
}
