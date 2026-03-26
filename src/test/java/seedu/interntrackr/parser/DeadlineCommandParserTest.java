package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.DeadlineCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineCommandParserTest {

    @Test
    public void parse_validArguments_returnsDeadlineCommand() throws InternTrackrException {
        assertTrue(DeadlineCommandParser.parse("add 1 t/Technical d/01-06-2026") instanceof DeadlineCommand);
    }

    @Test
    public void parse_validArgumentsWithNotes_returnsDeadlineCommand() throws InternTrackrException {
        assertTrue(DeadlineCommandParser.parse(
                "add 1 t/Interview d/15-07-2026 n/Prepare for coding round") instanceof DeadlineCommand);
    }

    @Test
    public void parse_missingAddSubcommand_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> DeadlineCommandParser.parse("1 t/Technical d/01-06-2026"));
    }

    @Test
    public void parse_invalidDateFormat_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> DeadlineCommandParser.parse("add 1 t/Technical d/2026-06-01"));
    }
}
