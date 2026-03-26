package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.AddCommand;
import seedu.interntrackr.command.DeleteCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parse_validAddCommand_returnsAddCommand() throws InternTrackrException {
        String input = "add c/Google r/Software Engineer";
        assertTrue(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parse_validDeleteCommand_returnsDeleteCommand() throws InternTrackrException {
        String input = "delete 1";
        assertTrue(Parser.parse(input) instanceof DeleteCommand);
    }

    @Test
    public void parse_addMissingParameters_throwsException() {
        String input = "add c/Google";
        assertThrows(InternTrackrException.class, () -> Parser.parse(input));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        String input = "invalidCommand";
        assertThrows(InternTrackrException.class, () -> Parser.parse(input));
    }
}
