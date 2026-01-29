package gabriel.exception;

/**
 * Represents all the exception that is specific to Gabriel chatbot
 * @author Gabriel Phua
 * @since 2026-01-22
 */
public class GabrielException extends Exception {

    /**
     * Constructs a new GabrielException instance with a specific error message.
     *
     * @param message The specific error message.
     */
    public GabrielException(String message) {
        super(message);
    }
}
