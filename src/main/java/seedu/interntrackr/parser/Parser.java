package seedu.interntrackr.parser;

import seedu.interntrackr.command.Command;
import seedu.interntrackr.command.ExitCommand;
import seedu.interntrackr.command.FilterCommand;
import seedu.interntrackr.command.StatusCommand;
import seedu.interntrackr.exception.InternTrackrException;

/**
 * Parses user input into executable commands.
 */
public class Parser {
    /**
     * Parses the full user command and returns the corresponding Command object.
     *
     * @param fullCommand The raw string inputted by the user.
     * @return The specific Command object to be executed.
     * @throws InternTrackrException If the command format is invalid.
     */
    public static Command parse(String fullCommand) throws InternTrackrException {
        // TODO: Implement string splitting and switch case for commands
        String commandWord = fullCommand.split(" ")[0];

        if (commandWord.equals("status")) {
            try {
                String[] parts = fullCommand.split(" s/", 2);
                if (parts.length < 2) {
                    throw new InternTrackrException("Invalid status format. Use: status INDEX s/STATUS");
                }
                int index = Integer.parseInt(parts[0].replace("status", "").trim());
                String statusValue = parts[1].replace("\"", "").trim();

                return new StatusCommand(index, statusValue);
            } catch (NumberFormatException e) {
                throw new InternTrackrException("Please provide a valid application index.");
            }
        }
        else if (commandWord.equals("filter")) {
            String arguments = fullCommand.replace("filter", "").trim();

            if (arguments.equals("clear")) {
                return new FilterCommand(true);
            }
            String[] parts = fullCommand.split(" s/", 2);
            if (parts.length < 2) {
                throw new InternTrackrException("Invalid filter format. Use: filter s/STATUS or filter clear");
            }
            String statusValue = parts[1].replace("\"", "").trim();
            return new FilterCommand(statusValue);
        }
        return new ExitCommand();
    }
}
