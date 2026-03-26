package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.FilterCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterCommandParserTest {

    @Test
    public void parse_validStatusFilter_returnsFilterCommand() throws InternTrackrException {
        assertTrue(FilterCommandParser.parse("s/Applied") instanceof FilterCommand);
    }

    @Test
    public void parse_clearArgument_returnsFilterCommand() throws InternTrackrException {
        assertTrue(FilterCommandParser.parse("clear") instanceof FilterCommand);
    }

    @Test
    public void parse_emptyArguments_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> FilterCommandParser.parse(""));
    }

    @Test
    public void parse_missingStatusPrefix_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> FilterCommandParser.parse("Applied"));
    }
}
