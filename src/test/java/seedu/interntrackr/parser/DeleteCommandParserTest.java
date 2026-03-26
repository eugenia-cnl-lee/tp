package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.DeleteCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandParserTest {

    @Test
    public void parse_validIndex_returnsDeleteCommand() throws InternTrackrException {
        assertTrue(DeleteCommandParser.parse("1") instanceof DeleteCommand);
    }

    @Test
    public void parse_largeValidIndex_returnsDeleteCommand() throws InternTrackrException {
        assertTrue(DeleteCommandParser.parse("99") instanceof DeleteCommand);
    }

    @Test
    public void parse_missingIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> DeleteCommandParser.parse(""));
    }

    @Test
    public void parse_nonNumericIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> DeleteCommandParser.parse("abc"));
    }
}
