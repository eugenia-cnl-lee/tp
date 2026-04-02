package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.ArchiveCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArchiveCommandParserTest {

    @Test
    public void parse_validIndex_returnsArchiveCommand() throws InternTrackrException {
        assertTrue(ArchiveCommandParser.parse("1") instanceof ArchiveCommand);
    }

    @Test
    public void parse_largeValidIndex_returnsArchiveCommand() throws InternTrackrException {
        assertTrue(ArchiveCommandParser.parse("99") instanceof ArchiveCommand);
    }


    @Test
    public void parse_whitespacePaddedIndex_returnsArchiveCommand() throws InternTrackrException {
        assertTrue(ArchiveCommandParser.parse("  2  ") instanceof ArchiveCommand);
    }

    @Test
    public void parse_missingIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> ArchiveCommandParser.parse(""));
    }

    @Test
    public void parse_nonNumericIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> ArchiveCommandParser.parse("abc"));
    }

    @Test
    public void parse_zeroIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> ArchiveCommandParser.parse("0"));
    }

    @Test
    public void parse_negativeIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> ArchiveCommandParser.parse("-1"));
    }

    @Test
    public void parse_decimalIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> ArchiveCommandParser.parse("1.5"));
    }
}
