package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.command.ExitCommand;
import seedu.interntrackr.command.ListArchiveCommand;
import seedu.interntrackr.command.ListCommand;
import seedu.interntrackr.command.OverviewCommand;
import seedu.interntrackr.command.ContactCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parse_listCommand_returnsListCommand() throws InternTrackrException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_listArchiveCommand_returnsListArchiveCommand() throws InternTrackrException {
        assertTrue(Parser.parse("list archive") instanceof ListArchiveCommand);
    }

    @Test
    public void parse_listArchiveCommandUpperCase_returnsListArchiveCommand() throws InternTrackrException {
        assertTrue(Parser.parse("list ARCHIVE") instanceof ListArchiveCommand);
    }

    @Test
    public void parse_listArchiveCommandMixedCase_returnsListArchiveCommand() throws InternTrackrException {
        assertTrue(Parser.parse("list Archive") instanceof ListArchiveCommand);
    }

    @Test
    public void parse_archiveCommand_returnsArchiveCommand() throws InternTrackrException {
        assertTrue(Parser.parse("archive 1") instanceof seedu.interntrackr.command.ArchiveCommand);
    }

    @Test
    public void parse_overviewCommand_returnsOverviewCommand() throws InternTrackrException {
        assertTrue(Parser.parse("overview") instanceof OverviewCommand);
    }

    @Test
    public void parse_exitCommand_returnsExitCommand() throws InternTrackrException {
        assertTrue(Parser.parse("exit") instanceof ExitCommand);
    }

    @Test
    public void parse_unknownCommand_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> Parser.parse("invalidCommand"));
    }

    @Test
    public void parse_blankInput_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> Parser.parse("   "));
    }


    @Test
    public void parse_listWithExtraArguments_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> Parser.parse("list archive extra"));
    }

    @Test
    public void parse_listWithUnknownSubcommand_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> Parser.parse("list archived"));
    }

    @Test
    public void parse_archiveWithoutIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class, () -> Parser.parse("archive"));
    }

    @Test
    void parse_contactCommand_success() throws Exception {
        assertTrue(Parser.parse("contact 1 c/Jane Tan e/jane@example.com") instanceof ContactCommand);
    }
}
