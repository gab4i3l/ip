import java.time.format.DateTimeParseException;

public class Parser {
    public static String getCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].toLowerCase();
    }

    public static int parseIndex(String input) throws GabrielException {
        try {
            String[] parts = input.split(" ");
            return Integer.parseInt(parts[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GabrielException("The number given is too big! You don't have that many tasks!");
        } catch (NumberFormatException e) {
            throw new GabrielException("That is not a number! Give me an actual number!");
        } catch (IndexOutOfBoundsException e) {
            throw new GabrielException("I don't think that task exist...");
        } catch (Exception e) {
            throw new GabrielException("The command cannot be processed. Please check if your input is in the correct format!");
        }
    }

    public static String parseToDo(String input) throws GabrielException {
        try {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            return description;
        } catch (StringIndexOutOfBoundsException e) {
            throw new GabrielException("The description given is empty!");
        } catch (Exception e) {
            throw new GabrielException("The command cannot be processed. Please check if your input is in the correct format!");
        }
    }

    public static String[] parseDeadline(String input) throws GabrielException {
        try {
            String content = input.substring(9).trim();
            String[] parts = content.split(" /by ");
            if (parts[0].trim().isEmpty()) {
                throw new GabrielException("The description given is empty!");
            }
            if (parts[1].trim().isEmpty()) {
                throw new GabrielException("The deadline given is empty!");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            return new String[]{description, by};
        } catch (StringIndexOutOfBoundsException e) {
            throw new GabrielException("The deadline command must follow the format: deadline <description> /by <time>");
        } catch (DateTimeParseException e) {
            throw new GabrielException("I can't understand that date! Please use the format: yyyy-MM-dd HHmm");
        }catch (Exception e) {
            throw new GabrielException("The command cannot be processed. Please check if your input is in the correct format!");
        }
    }

    public static String[] parseEvent(String input) throws GabrielException {
        try {
            String eventInput = input.substring(6).trim();
            if (eventInput.startsWith("/from")) {
                throw new GabrielException("The description of an event cannot be empty!");
            }
            String[] eventParts = eventInput.split(" /from ");
            String eventDescription = eventParts[0].trim();
            String[] timeParts = eventParts[1].split(" /to ");
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            if (from.isEmpty() || to.isEmpty()) {
                System.out.println("The /from or /to is empty!");
            }
            return new String[]{eventDescription, from, to};
        } catch (StringIndexOutOfBoundsException e) {
            throw new GabrielException("The deadline command must follow the format: event <description> /from <time> /to <time>");
        } catch (DateTimeParseException e) {
            throw new GabrielException("I can't understand that date! Please use the format: yyyy-MM-dd HHmm");
        }catch (Exception e) {
            throw new GabrielException("The command cannot be processed. Please check if your input is in the correct format!");
        }
    }
}
