package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.AddCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandParserTest {

    @Test
    public void parse_validArguments_returnsAddCommand() throws InternTrackrException {
        assertTrue(AddCommandParser.parse("c/Google r/Software Engineer") instanceof AddCommand);
    }

    @Test
    public void parse_reversedPrefixOrder_returnsAddCommand() throws InternTrackrException {
        assertTrue(AddCommandParser.parse("r/Software Engineer c/Google") instanceof AddCommand);
    }

    @Test
    public void parse_missingCompanyPrefix_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> AddCommandParser.parse("r/Software Engineer"));
    }

    @Test
    public void parse_missingRolePrefix_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> AddCommandParser.parse("c/Google"));
    }
}
