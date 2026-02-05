package gabriel.parser;

import gabriel.exception.GabrielException;

/**
 * Translate user input into commands and data the chatbot can work with.
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class Parser {

    /**
     * Extracts the command from the user input.
     * @param input The raw user input.
     * @return The command given by user.
     */
    public static String getCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].toLowerCase();
    }

    /**
     * Extracts the index pointing to a task from the user input.
     * @param input The raw user input.
     * @return The index in integer, pointing to a task.
     * @throws GabrielException if the input is not a number or in a wrong format.
     */
    public static int parseIndex(String input) throws GabrielException {
        try {
            String[] parts = input.split(" ");
            return Integer.parseInt(parts[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GabrielException("The number you entered is invalid!");
        } catch (NumberFormatException e) {
            throw new GabrielException("That is not a number! Give me an actual number!");
        } catch (Exception e) {
            throw new GabrielException("The command cannot be processed."
                    + " Please check if your input is in the correct format!");
        }
    }

    /**
     * Extracts the description for a ToDo task from user input.
     * @param input The raw user input.
     * @return The description of the task.
     * @throws GabrielException if the description is empty or the input is in a wrong format.
     */
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
            throw new GabrielException("The command cannot be processed."
                    + " Please check if your input is in the correct format!");
        }
    }

    /**
     * Extracts the description and due date of a Deadline task.
     * @param input The raw input from user due date of the Deadline task.
     * @return A String array containing the description and due date of the Deadline task.
     * @throws GabrielException if description and/or deadline is missing or input is in a wrong format.
     */
    public static String[] parseDeadline(String input) throws GabrielException {
        try {
            String content = input.substring(9).trim();

            if (content.isEmpty()) {
                throw new GabrielException("The deadline command must follow the format:"
                        + " deadline <description> /by <time>");
            }

            if (content.startsWith("/by")) {
                throw new GabrielException("The description given is empty!");
            }

            String[] parts = content.split("/by", 2);

            if (parts.length < 2) {
                throw new GabrielException("The deadline command must follow the format:"
                        + " deadline <description> /by <time>");
            }

            String description = parts[0].trim();
            String by = parts[1].trim();

            if (description.isEmpty()) {
                throw new GabrielException("The description given is empty!");
            }

            if (by.isEmpty()) {
                throw new GabrielException("The deadline given is empty!");
            }

            return new String[]{description, by};
        } catch (StringIndexOutOfBoundsException e) {
            throw new GabrielException("The deadline command must follow the format:"
                    + " deadline <description> /by <time>");
        } catch (GabrielException e) {
            throw e;
        } catch (Exception e) {
            throw new GabrielException("The command cannot be processed."
                    + " Please check if your input is in the correct format!");
        }
    }

    /**
     * Extracts the description, start and end time of an Event task.
     * @param input The raw user input.
     * @return String array containing the description, start and end time of the Event task.
     * @throws GabrielException if description and/or start time and/or end time is missing or wrong format
     *
     */
    public static String[] parseEvent(String input) throws GabrielException {
        try {
            String eventInput = input.substring(6).trim();

            if (eventInput.isEmpty()) {
                throw new GabrielException("The event command must follow the format:"
                        + " event <description> /from <time> /to <time>");
            }

            if (eventInput.startsWith("/from")) {
                throw new GabrielException("The description of an event cannot be empty!");
            }

            String[] eventParts = eventInput.split("/from");
            if (eventParts.length < 2) {
                throw new GabrielException("The event command must follow the format:"
                        + " event <description> /from <time> /to <time>");
            }

            String eventDescription = eventParts[0].trim();
            String[] timeParts = eventParts[1].split("/to");

            if (timeParts.length < 2) {
                throw new GabrielException("The event command must follow the format:"
                        + " event <description> /from <time> /to <time>");
            }

            String from = timeParts[0].trim();
            String to = timeParts[1].trim();

            if (from.isEmpty() || to.isEmpty()) {
                throw new GabrielException("The /from or /to is empty!");
            }

            return new String[]{eventDescription, from, to};
        } catch (StringIndexOutOfBoundsException e) {
            throw new GabrielException("The event command must follow the format:"
                    + " event <description> /from <time> /to <time>");
        } catch (GabrielException e) {
            throw e;
        } catch (Exception e) {
            throw new GabrielException("The command cannot be processed."
                    + " Please check if your input is in the correct format!");
        }
    }

    /**
     * Extracts the keyword for find command.
     *
     * @param input The raw user input.
     * @return The keyword to search for.
     * @throws GabrielException when keyword is not given or command is in the wrong format.
     */
    public static String parseFindKeyword(String input) throws GabrielException {
        try {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new GabrielException("The keyword cannot be empty!");
            }
            return keyword;
        } catch (GabrielException e) {
            throw e;
        } catch (Exception e) {
            throw new GabrielException("The command cannot be processed."
                    + " Please check if your input is in the correct format!");
        }
    }
}
