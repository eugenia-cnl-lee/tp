package seedu.interntrackr.ui;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Handles terminal reading and writing formatting.
 */
public class Ui {
    private static final Logger logger = Logger.getLogger(Ui.class.getName());
    private static final String LINE = "____________________________________________________________";
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
        assert this.in != null : "Scanner must be initialized";
        logger.fine("Ui initialized.");
    }

    public void showWelcome() {
        logger.fine("Showing welcome message.");
        System.out.println(LINE);
        System.out.println("Welcome to InternTrackr! Ready to hunt for some internships?");
        System.out.println(LINE);
    }

    /**
     * Reads a command from user input.
     *
     * @return trimmed, non-null input string
     */
    public String readCommand() {
        assert in != null : "Scanner must not be null when reading input";

        String input = in.nextLine();

        if (input == null) {
            logger.warning("readCommand() received null input; defaulting to empty string.");
            input = "";
        }

        String trimmed = input.trim();
        logger.fine("Command read: \"" + trimmed + "\"");
        return trimmed;
    }

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to display; must not be null
     */
    public void showMessage(String message) {
        assert message != null : "message must not be null";
        if (message == null) {
            logger.warning("showMessage() called with null message; skipping.");
            return;
        }
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display; must not be null
     */
    public void showError(String message) {
        assert message != null : "error message must not be null";
        String safeMessage = (message != null) ? message : "Unknown error";
        logger.warning("Displaying error to user: " + safeMessage);
        System.out.println("Error: " + safeMessage);
    }

    public void showLoadingError() {
        logger.info("No existing data found. Starting fresh.");
        System.out.println("No existing data found. Starting with a fresh tracker.");
    }
}
