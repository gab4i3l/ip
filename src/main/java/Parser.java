public class Parser {
    public static String getCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].toLowerCase();
    }
}
