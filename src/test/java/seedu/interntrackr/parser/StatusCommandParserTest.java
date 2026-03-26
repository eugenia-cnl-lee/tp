package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.StatusCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusCommandParserTest {

    @Test
    public void parse_validArguments_returnsStatusCommand() throws InternTrackrException {
        assertTrue(StatusCommandParser.parse("1 s/Applied") instanceof StatusCommand);
    }

    @Test
    public void parse_validArgumentsDifferentStatus_returnsStatusCommand() throws InternTrackrException {
        assertTrue(StatusCommandParser.parse("2 s/Offered") instanceof StatusCommand);
    }

    @Test
    public void parse_missingStatusPrefix_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> StatusCommandParser.parse("1 Applied"));
    }

    @Test
    public void parse_nonNumericIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> StatusCommandParser.parse("abc s/Applied"));
    }
}
